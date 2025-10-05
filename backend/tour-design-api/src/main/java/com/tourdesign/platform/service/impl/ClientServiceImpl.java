package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.ClientEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.ClientModel;
import com.tourdesign.platform.mapper.ClientMapper;
import com.tourdesign.platform.repository.ClientRepository;
import com.tourdesign.platform.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public List<ClientModel> list() {
        List<ClientEntity> listEntity = repository.findAll();
        return mapper.toModelList(listEntity);
    }

    @Override
    public ClientModel create(ClientModel obj) {
        ClientEntity entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<ClientModel> search(Long id) {
        ClientEntity entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        ClientModel model = mapper.toModel(entity);
        return Optional.ofNullable(model);
    }

    @Override
    public Optional<ClientModel> update(Long id, ClientModel obj) {
        ClientEntity entity = mapper.toEntity(obj);
        return repository.findById(id)
                .map(existingEntity -> {
                    mapper.patchEntity(existingEntity, entity);
                    ClientEntity updatedEntity = repository.save(existingEntity);
                    return mapper.toModel(updatedEntity);
                });
    }

    @Override
    public boolean delete(Long id) {
        return repository.findById(id).map(obj -> {
            repository.delete(obj);
            return true;
        }).orElse(false);
    }
}