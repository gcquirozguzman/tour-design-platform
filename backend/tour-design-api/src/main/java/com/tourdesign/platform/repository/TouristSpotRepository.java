package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.TouristSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristSpotRepository extends JpaRepository<TouristSpotEntity, Long> {
}
