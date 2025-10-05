package com.tourdesign.platform.service;

import com.tourdesign.platform.model.HotelModel;
import java.util.List;
import java.util.Optional;

public interface HotelService {

    List<HotelModel> list();

    HotelModel create(HotelModel obj);

    Optional<HotelModel> search(Long id);

    Optional<HotelModel> update(Long id, HotelModel obj);

    boolean delete(Long id);
}
