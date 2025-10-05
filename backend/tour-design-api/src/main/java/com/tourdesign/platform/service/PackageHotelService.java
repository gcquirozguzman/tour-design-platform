package com.tourdesign.platform.service;

import com.tourdesign.platform.model.PackageHotelModel;
import java.util.List;
import java.util.Optional;

public interface PackageHotelService {

    List<PackageHotelModel> list();

    PackageHotelModel create(PackageHotelModel obj);

    Optional<PackageHotelModel> search(Long id);

    List<PackageHotelModel> findByTravelPackageId(Long packageId);

    Optional<PackageHotelModel> update(Long id, PackageHotelModel obj);

    boolean delete(Long id);
}
