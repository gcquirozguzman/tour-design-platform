package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.RecommendationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationHistoryRepository extends JpaRepository<RecommendationHistoryEntity, Long> {
}
