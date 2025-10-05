package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.dto.request.RecommendationRequest;
import com.tourdesign.platform.dto.response.RecommendationResponse;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.mapper.*;
import com.tourdesign.platform.model.*;
import com.tourdesign.platform.repository.*;
import com.tourdesign.platform.service.*;
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
    private ClientService clientService;

    @Autowired
    private ClientPreferenceService clientPreferenceService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private TouristSpotService touristSpotService;

    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private PackageHotelService packageHotelService;

    @Autowired
    private PackageRestaurantService packageRestaurantService;

    @Autowired
    private PackageSpotService packageSpotService;

    @Autowired
    private RecommendationHistoryService recommendationHistoryService;

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
        ClientModel client = clientService.search(request.getClientId())
                .orElseThrow(() -> new DataNotFoundException(request.getClientId()));

        // 2) Load preferences by IDs (if none provided, prefer client's own preferences)
        List<ClientPreferenceModel> chosenPrefs = new ArrayList<>();
        if (request.getPreferenceIds() != null && !request.getPreferenceIds().isEmpty()) {
            // findAllById returns iterable of existing ids
            chosenPrefs.addAll(clientPreferenceService.searchAllById(request.getPreferenceIds()));
        } else {
            // fallback to client's stored preferences
            List<ClientPreferenceModel> clientPrefs = clientPreferenceService.getPreferencesByClient(client.getId());
            if (clientPrefs != null && !clientPrefs.isEmpty()) {
                chosenPrefs.addAll(clientPrefs);
            }
        }

        // 3) fetch catalogs
        List<HotelModel> hotels = hotelService.list();
        List<RestaurantModel> restaurants = restaurantService.list();
        List<TouristSpotModel> spots = touristSpotService.list();

        if (hotels.isEmpty() || restaurants.isEmpty() || spots.isEmpty()) {
            throw new IllegalStateException("Not enough catalog data to build a package (hotels/restaurants/spots required).");
        }

        // 4) try to pick matching items by preferences
        List<String> prefCats = chosenPrefs.stream()
                .map(p -> p.getCategory() == null ? "" : p.getCategory().toLowerCase().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        HotelModel chosenHotel = pickMatchingHotel(hotels, prefCats);
        RestaurantModel chosenRestaurant = pickMatchingRestaurant(restaurants, prefCats);
        List<TouristSpotModel> chosenSpots = pickMatchingSpots(spots, prefCats, 3);

        // 5) create TravelPackageModel
        TravelPackageModel travelPackage = new TravelPackageModel();
        String pkgName = buildPackageName(client, prefCats);
        travelPackage.setName(pkgName);
        travelPackage.setDescription("Auto-generated package based on preferences: " + String.join(", ", prefCats));
        travelPackage.setDurationDays( (chosenSpots.size() <= 0) ? 1 : Math.min(7, 2 + chosenSpots.size()) );
        // estimate price: sum of (hotel.pricePerNight * duration) + avg restaurant price + sum spot entrance approx
        double estPrice = estimatePrice(chosenHotel, chosenRestaurant, chosenSpots, travelPackage.getDurationDays());
        travelPackage.setEstimatedPrice(estPrice);
        travelPackage.setFocusType(prefCats.isEmpty() ? "General" : String.join(",", prefCats));
        travelPackage = travelPackageService.create(travelPackage);

        // 6) save package relationships
        PackageHotelModel phe = new PackageHotelModel();
        phe.setOrderIndex(1);
        phe.setTravelPackage(travelPackage);
        phe.setHotel(chosenHotel);
        packageHotelService.create(phe);

        PackageRestaurantModel pre = new PackageRestaurantModel();
        pre.setOrderIndex(1);
        pre.setTravelPackage(travelPackage);
        pre.setRestaurant(chosenRestaurant);
        packageRestaurantService.create(pre);

        int idx = 1;
        for (TouristSpotModel s : chosenSpots) {
            PackageSpotModel pse = new PackageSpotModel();
            pse.setOrderIndex(idx++);
            pse.setTravelPackage(travelPackage);
            pse.setTouristSpot(s);
            packageSpotService.create(pse);
        }

        // 7) save recommendation history
        RecommendationHistoryModel history = new RecommendationHistoryModel();
        history.setClient(client);
        history.setTravelPackage(travelPackage);
        history.setRecommendationDate(LocalDateTime.now());
        history.setAccepted(false);
        RecommendationHistoryModel savedHistory = recommendationHistoryService.create(history);

        // 8) build response (map entities -> models)

        return RecommendationResponse.builder()
                .client(client)
                .preferences(chosenPrefs)
                .travelPackage(travelPackage)
                .hotel(chosenHotel)
                .restaurant(chosenRestaurant)
                .spots(chosenSpots)
                .history(savedHistory)
                .build();
    }

    private HotelModel pickMatchingHotel(List<HotelModel> hotels, List<String> prefCats) {
        if (prefCats.isEmpty()) return randomFromList(hotels);

        List<HotelModel> filtered = hotels.stream()
                .filter(h -> {
                    String loc = h.getLocation() == null ? "" : h.getLocation().toLowerCase();
                    return prefCats.stream().anyMatch(loc::contains);
                }).collect(Collectors.toList());

        return filtered.isEmpty() ? randomFromList(hotels) : randomFromList(filtered);
    }

    private RestaurantModel pickMatchingRestaurant(List<RestaurantModel> restaurants, List<String> prefCats) {
        if (prefCats.isEmpty()) return randomFromList(restaurants);

        List<RestaurantModel> filtered = restaurants.stream()
                .filter(r -> {
                    String cuisine = r.getCuisineType() == null ? "" : r.getCuisineType().toLowerCase();
                    return prefCats.stream().anyMatch(cuisine::contains);
                }).collect(Collectors.toList());

        return filtered.isEmpty() ? randomFromList(restaurants) : randomFromList(filtered);
    }

    private List<TouristSpotModel> pickMatchingSpots(List<TouristSpotModel> spots, List<String> prefCats, int max) {
        List<TouristSpotModel> pool;
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

    private String buildPackageName(ClientModel client, List<String> prefCats) {
        String base = "Package for " + client.getFirstName();
        if (!prefCats.isEmpty()) {
            base += " - " + prefCats.stream().map(String::toLowerCase).map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1)).collect(Collectors.joining(", "));
        }
        return base;
    }

    private double estimatePrice(HotelModel hotel, RestaurantModel restaurant, List<TouristSpotModel> spots, int days) {
        double hotelTotal = (hotel.getPricePerNight() == null ? 0.0 : hotel.getPricePerNight() * days);
        double restaurantAvg = (restaurant.getAveragePrice() == null ? 0.0 : restaurant.getAveragePrice());
        double spotsSum = spots.stream().mapToDouble(s -> s.getEntranceFee() == null ? 0.0 : s.getEntranceFee()).sum();
        return Math.round((hotelTotal + restaurantAvg + spotsSum) * 100.0) / 100.0;
    }


}