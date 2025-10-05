package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.dto.request.RecommendationRequest;
import com.tourdesign.platform.dto.response.RecommendationResponse;
import com.tourdesign.platform.entity.*;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.mapper.*;
import com.tourdesign.platform.model.*;
import com.tourdesign.platform.repository.*;
import com.tourdesign.platform.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientPreferenceRepository clientPreferenceRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TouristSpotRepository touristSpotRepository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private PackageHotelRepository packageHotelRepository;

    @Autowired
    private PackageRestaurantRepository packageRestaurantRepository;

    @Autowired
    private PackageSpotRepository packageSpotRepository;

    @Autowired
    private RecommendationHistoryRepository recommendationHistoryRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientPreferenceMapper clientPreferenceMapper;

    @Autowired
    private TravelPackageMapper travelPackageMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private TouristSpotMapper touristSpotMapper;

    @Autowired
    private RecommendationHistoryMapper recommendationHistoryMapper;

    private final Random rnd = new Random();

    @Override
    @Transactional
    public RecommendationResponse createRecommendation(RecommendationRequest request) {
        // 1) Validate client
        ClientEntity client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new DataNotFoundException(request.getClientId()));

        // 2) Load preferences by IDs (if none provided, prefer client's own preferences)
        List<ClientPreferenceEntity> chosenPrefs = new ArrayList<>();
        if (request.getPreferenceIds() != null && !request.getPreferenceIds().isEmpty()) {
            // findAllById returns iterable of existing ids
            clientPreferenceRepository.findAllById(request.getPreferenceIds())
                    .forEach(chosenPrefs::add);
        } else {
            // fallback to client's stored preferences
            List<ClientPreferenceEntity> clientPrefs = clientPreferenceRepository.findByClientId(client.getId());
            if (clientPrefs != null && !clientPrefs.isEmpty()) {
                chosenPrefs.addAll(clientPrefs);
            }
        }

        // 3) fetch catalogs
        List<HotelEntity> hotels = hotelRepository.findAll();
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();
        List<TouristSpotEntity> spots = touristSpotRepository.findAll();

        if (hotels.isEmpty() || restaurants.isEmpty() || spots.isEmpty()) {
            throw new IllegalStateException("Not enough catalog data to build a package (hotels/restaurants/spots required).");
        }

        // 4) try to pick matching items by preferences
        List<String> prefCats = chosenPrefs.stream()
                .map(p -> p.getCategory() == null ? "" : p.getCategory().toLowerCase().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        HotelEntity chosenHotel = pickMatchingHotel(hotels, prefCats);
        RestaurantEntity chosenRestaurant = pickMatchingRestaurant(restaurants, prefCats);
        List<TouristSpotEntity> chosenSpots = pickMatchingSpots(spots, prefCats, 3);

        // 5) create TravelPackageEntity
        TravelPackageEntity travelPackage = new TravelPackageEntity();
        String pkgName = buildPackageName(client, prefCats);
        travelPackage.setName(pkgName);
        travelPackage.setDescription("Auto-generated package based on preferences: " + String.join(", ", prefCats));
        travelPackage.setDurationDays( (chosenSpots.size() <= 0) ? 1 : Math.min(7, 2 + chosenSpots.size()) );
        // estimate price: sum of (hotel.pricePerNight * duration) + avg restaurant price + sum spot entrance approx
        double estPrice = estimatePrice(chosenHotel, chosenRestaurant, chosenSpots, travelPackage.getDurationDays());
        travelPackage.setEstimatedPrice(estPrice);
        travelPackage.setFocusType(prefCats.isEmpty() ? "General" : String.join(",", prefCats));
        travelPackage = travelPackageRepository.save(travelPackage);

        // 6) save package relationships
        PackageHotelEntity phe = new PackageHotelEntity();
        phe.setOrderIndex(1);
        phe.setTravelPackage(travelPackage);
        phe.setHotel(chosenHotel);
        packageHotelRepository.save(phe);

        PackageRestaurantEntity pre = new PackageRestaurantEntity();
        pre.setOrderIndex(1);
        pre.setTravelPackage(travelPackage);
        pre.setRestaurant(chosenRestaurant);
        packageRestaurantRepository.save(pre);

        int idx = 1;
        for (TouristSpotEntity s : chosenSpots) {
            PackageSpotEntity pse = new PackageSpotEntity();
            pse.setOrderIndex(idx++);
            pse.setTravelPackage(travelPackage);
            pse.setTouristSpot(s);
            packageSpotRepository.save(pse);
        }

        // 7) save recommendation history
        RecommendationHistoryEntity history = new RecommendationHistoryEntity();
        history.setClient(client);
        history.setTravelPackage(travelPackage);
        history.setRecommendationDate(LocalDateTime.now());
        history.setAccepted(false);
        RecommendationHistoryEntity savedHistory = recommendationHistoryRepository.save(history);

        // 8) build response (map entities -> models)
        RecommendationResponse response = RecommendationResponse.builder()
                .client(clientMapper.toModel(client))
                .preferences(clientPreferenceMapper.toModelList(chosenPrefs))
                .travelPackage(travelPackageMapper.toModel(travelPackage))
                .hotel(hotelMapper.toModel(chosenHotel))
                .restaurant(restaurantMapper.toModel(chosenRestaurant))
                .spots(chosenSpots.stream().map(touristSpotMapper::toModel).collect(Collectors.toList()))
                .history(recommendationHistoryMapper.toModel(savedHistory))
                .build();

        return response;
    }

    private HotelEntity pickMatchingHotel(List<HotelEntity> hotels, List<String> prefCats) {
        if (prefCats.isEmpty()) return randomFromList(hotels);

        List<HotelEntity> filtered = hotels.stream()
                .filter(h -> {
                    String loc = h.getLocation() == null ? "" : h.getLocation().toLowerCase();
                    return prefCats.stream().anyMatch(loc::contains);
                }).collect(Collectors.toList());

        return filtered.isEmpty() ? randomFromList(hotels) : randomFromList(filtered);
    }

    private RestaurantEntity pickMatchingRestaurant(List<RestaurantEntity> restaurants, List<String> prefCats) {
        if (prefCats.isEmpty()) return randomFromList(restaurants);

        List<RestaurantEntity> filtered = restaurants.stream()
                .filter(r -> {
                    String cuisine = r.getCuisineType() == null ? "" : r.getCuisineType().toLowerCase();
                    return prefCats.stream().anyMatch(cuisine::contains);
                }).collect(Collectors.toList());

        return filtered.isEmpty() ? randomFromList(restaurants) : randomFromList(filtered);
    }

    private List<TouristSpotEntity> pickMatchingSpots(List<TouristSpotEntity> spots, List<String> prefCats, int max) {
        List<TouristSpotEntity> pool;
        if (prefCats.isEmpty()) {
            pool = new ArrayList<>(spots);
        } else {
            pool = spots.stream()
                    .filter(s -> {
                        String type = s.getSpotType() == null ? "" : s.getSpotType().toLowerCase();
                        return prefCats.stream().anyMatch(type::contains);
                    }).collect(Collectors.toList());
            if (pool.isEmpty()) pool = new ArrayList<>(spots); // fallback
        }

        Collections.shuffle(pool, rnd);
        return pool.stream().limit(max).collect(Collectors.toList());
    }

    private <T> T randomFromList(List<T> list) {
        return list.get(rnd.nextInt(list.size()));
    }

    private String buildPackageName(ClientEntity client, List<String> prefCats) {
        String base = "Package for " + client.getFirstName();
        if (!prefCats.isEmpty()) {
            base += " - " + prefCats.stream().map(String::toLowerCase).map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1)).collect(Collectors.joining(", "));
        }
        return base;
    }

    private double estimatePrice(HotelEntity hotel, RestaurantEntity restaurant, List<TouristSpotEntity> spots, int days) {
        double hotelTotal = (hotel.getPricePerNight() == null ? 0.0 : hotel.getPricePerNight() * days);
        double restaurantAvg = (restaurant.getAveragePrice() == null ? 0.0 : restaurant.getAveragePrice());
        double spotsSum = spots.stream().mapToDouble(s -> s.getEntranceFee() == null ? 0.0 : s.getEntranceFee()).sum();
        return Math.round((hotelTotal + restaurantAvg + spotsSum) * 100.0) / 100.0;
    }


}