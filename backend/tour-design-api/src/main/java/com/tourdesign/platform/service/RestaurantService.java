package com.tourdesign.platform.service;

import com.tourdesign.platform.model.RestaurantModel;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    List<RestaurantModel> list();

    RestaurantModel create(RestaurantModel obj);

    Optional<RestaurantModel> search(Long id);

    Optional<RestaurantModel> update(Long id, RestaurantModel obj);

    boolean delete(Long id);
}
