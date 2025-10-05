package com.tourdesign.platform.service;

import com.tourdesign.platform.model.ClientPreferenceModel;
import java.util.List;
import java.util.Optional;

public interface ClientPreferenceService {

    List<ClientPreferenceModel> list();

    ClientPreferenceModel create(ClientPreferenceModel obj);

    Optional<ClientPreferenceModel> search(Long id);

    Optional<ClientPreferenceModel> update(Long id, ClientPreferenceModel obj);

    boolean delete(Long id);
}
