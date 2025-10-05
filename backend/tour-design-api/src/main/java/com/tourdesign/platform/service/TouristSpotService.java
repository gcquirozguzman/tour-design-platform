package com.tourdesign.platform.service;

import com.tourdesign.platform.model.TouristSpotModel;
import java.util.List;
import java.util.Optional;

public interface TouristSpotService {

    List<TouristSpotModel> list();

    TouristSpotModel create(TouristSpotModel obj);

    Optional<TouristSpotModel> search(Long id);

    Optional<TouristSpotModel> update(Long id, TouristSpotModel obj);

    boolean delete(Long id);
}
