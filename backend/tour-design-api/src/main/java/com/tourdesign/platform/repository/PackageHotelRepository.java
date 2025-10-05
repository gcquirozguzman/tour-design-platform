package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.PackageHotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageHotelRepository extends JpaRepository<PackageHotelEntity, Long> {

    List<PackageHotelEntity> findByTravelPackageId(Long travelPackageId);

}
