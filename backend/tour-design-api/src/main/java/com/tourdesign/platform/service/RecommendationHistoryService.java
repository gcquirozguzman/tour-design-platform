package com.tourdesign.platform.service;

import com.tourdesign.platform.model.RecommendationHistoryModel;
import java.util.List;
import java.util.Optional;

public interface RecommendationHistoryService {

    List<RecommendationHistoryModel> list();

    RecommendationHistoryModel create(RecommendationHistoryModel obj);

    Optional<RecommendationHistoryModel> search(Long id);

    Optional<RecommendationHistoryModel> update(Long id, RecommendationHistoryModel obj);

    boolean delete(Long id);

    List<RecommendationHistoryModel> searchByClient(Long clientId);
}