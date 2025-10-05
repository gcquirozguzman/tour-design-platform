package com.tourdesign.platform.service;

import com.tourdesign.platform.model.PackageRestaurantModel;
import java.util.List;
import java.util.Optional;

public interface PackageRestaurantService {

    List<PackageRestaurantModel> list();

    PackageRestaurantModel create(PackageRestaurantModel obj);

    Optional<PackageRestaurantModel> search(Long id);

    List<PackageRestaurantModel> findByTravelPackageId(Long packageId);

    Optional<PackageRestaurantModel> update(Long id, PackageRestaurantModel obj);

    boolean delete(Long id);
}
