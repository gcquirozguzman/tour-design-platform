package com.tourdesign.platform.service;

import com.tourdesign.platform.model.TravelPackageModel;
import java.util.List;
import java.util.Optional;

public interface TravelPackageService {

    List<TravelPackageModel> list();

    TravelPackageModel create(TravelPackageModel obj);

    Optional<TravelPackageModel> search(Long id);

    Optional<TravelPackageModel> searchDetail(Long id);

    Optional<TravelPackageModel> update(Long id, TravelPackageModel obj);

    boolean delete(Long id);
}
