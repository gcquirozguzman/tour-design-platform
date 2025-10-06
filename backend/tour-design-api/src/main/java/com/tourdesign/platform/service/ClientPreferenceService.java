package com.tourdesign.platform.service;

import com.tourdesign.platform.entity.ClientPreferenceEntity;
import com.tourdesign.platform.model.ClientPreferenceModel;
import java.util.List;
import java.util.Optional;

public interface ClientPreferenceService {

    List<ClientPreferenceModel> list();

    ClientPreferenceModel create(ClientPreferenceModel obj);

    List<ClientPreferenceModel> getPreferencesByClient(Long clientId);

    Optional<ClientPreferenceModel> search(Long id);

    List<ClientPreferenceModel> searchAllById(List<Long> preferenceIds);

    Optional<ClientPreferenceModel> update(Long id, ClientPreferenceModel obj);

    boolean delete(Long id);

    List<ClientPreferenceModel> searchByClient(Long clientId);
}
