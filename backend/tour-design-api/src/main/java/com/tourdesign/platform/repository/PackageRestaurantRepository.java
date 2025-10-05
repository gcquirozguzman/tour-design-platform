package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.PackageHotelEntity;
import com.tourdesign.platform.entity.PackageRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRestaurantRepository extends JpaRepository<PackageRestaurantEntity, Long> {

    List<PackageRestaurantEntity> findByTravelPackageId(Long travelPackageId);

}
