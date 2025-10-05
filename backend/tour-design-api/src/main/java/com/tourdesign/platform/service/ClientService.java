package com.tourdesign.platform.service;

import com.tourdesign.platform.model.ClientModel;
import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientModel> list();

    ClientModel create(ClientModel obj);

    Optional<ClientModel> search(Long id);

    List<ClientModel> searchByNameOrLastname(String query);

    Optional<ClientModel> update(Long id, ClientModel obj);

    boolean delete(Long id);
}