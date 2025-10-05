package com.tourdesign.platform.service;

import com.tourdesign.platform.model.PackageSpotModel;
import java.util.List;
import java.util.Optional;

public interface PackageSpotService {

    List<PackageSpotModel> list();

    PackageSpotModel create(PackageSpotModel obj);

    Optional<PackageSpotModel> search(Long id);

    Optional<PackageSpotModel> update(Long id, PackageSpotModel obj);

    boolean delete(Long id);
}
