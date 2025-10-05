package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.PackageRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRestaurantRepository extends JpaRepository<PackageRestaurantEntity, Long> {
}
