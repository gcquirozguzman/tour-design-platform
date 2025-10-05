package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.PackageRestaurantEntity;
import com.tourdesign.platform.entity.PackageSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageSpotRepository extends JpaRepository<PackageSpotEntity, Long> {

    List<PackageSpotEntity> findByTravelPackageId(Long travelPackageId);

}
