package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.ClientEntity;
import com.tourdesign.platform.entity.RecommendationHistoryEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.mapper.*;
import com.tourdesign.platform.model.RecommendationHistoryModel;
import com.tourdesign.platform.repository.*;
import com.tourdesign.platform.service.PackageHotelService;
import com.tourdesign.platform.service.PackageRestaurantService;
import com.tourdesign.platform.service.PackageSpotService;
import com.tourdesign.platform.service.RecommendationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationHistoryServiceImpl implements RecommendationHistoryService {

    @Autowired
    private RecommendationHistoryRepository recommendationHistoryRepository;

    @Autowired
    private RecommendationHistoryMapper recommendationHistoryMapper;

    @Autowired
    private PackageHotelService packageHotelService;

    @Autowired
    private PackageRestaurantService packageRestaurantService;

    @Autowired
    private PackageSpotService packageSpotService;

    @Override
    public List<RecommendationHistoryModel> list() {
        return recommendationHistoryMapper.toModelList(recommendationHistoryRepository.findAll());
    }

    @Override
    public RecommendationHistoryModel create(RecommendationHistoryModel obj) {
        var entity = recommendationHistoryMapper.toEntity(obj);
        entity = recommendationHistoryRepository.save(entity);
        return recommendationHistoryMapper.toModel(entity);
    }

    @Override
    public Optional<RecommendationHistoryModel> search(Long id) {
        var entity = recommendationHistoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(recommendationHistoryMapper.toModel(entity));
    }

    @Override
    public Optional<RecommendationHistoryModel> update(Long id, RecommendationHistoryModel obj) {
        var entity = recommendationHistoryMapper.toEntity(obj);
        return recommendationHistoryRepository.findById(id).map(existing -> {
            recommendationHistoryMapper.patchEntity(existing, entity);
            var updated = recommendationHistoryRepository.save(existing);
            return recommendationHistoryMapper.toModel(updated);
        });
    }

    @Override
    public boolean delete(Long id) {
        return recommendationHistoryRepository.findById(id).map(e -> {
            recommendationHistoryRepository.delete(e);
            return true;
        }).orElse(false);
    }

    @Override
    public List<RecommendationHistoryModel> searchByClient(Long clientId) {

        List<RecommendationHistoryEntity> listRecommendationEntity = recommendationHistoryRepository.findByClientId(clientId);
        List<RecommendationHistoryModel> listRecommendationModel = recommendationHistoryMapper.toModelList(listRecommendationEntity);

        for(RecommendationHistoryModel recommendation: listRecommendationModel){
            recommendation.getTravelPackage().setHotels(packageHotelService.findByTravelPackageId(recommendation.getTravelPackage().getId()));
            recommendation.getTravelPackage().setRestaurants(packageRestaurantService.findByTravelPackageId(recommendation.getTravelPackage().getId()));
            recommendation.getTravelPackage().setSpots(packageSpotService.findByTravelPackageId(recommendation.getTravelPackage().getId()));
        }

        return listRecommendationModel;
    }
}
