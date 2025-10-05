package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.RecommendationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationHistoryRepository extends JpaRepository<RecommendationHistoryEntity, Long> {

    Optional<RecommendationHistoryEntity> findTopByClientIdOrderByRecommendationDateDesc(Long clientId);

    List<RecommendationHistoryEntity> findByClientId(Long clientId);

}
