package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.ClientPreferenceEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.ClientPreferenceModel;
import com.tourdesign.platform.mapper.ClientPreferenceMapper;
import com.tourdesign.platform.repository.ClientPreferenceRepository;
import com.tourdesign.platform.service.ClientPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientPreferenceServiceImpl implements ClientPreferenceService {

    @Autowired
    private ClientPreferenceRepository repository;

    @Autowired
    private ClientPreferenceMapper mapper;

    @Override
    public List<ClientPreferenceModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public ClientPreferenceModel create(ClientPreferenceModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public List<ClientPreferenceModel> getPreferencesByClient(Long clientId) {
        return mapper.toModelList(repository.findByClientId(clientId));
    }

    @Override
    public Optional<ClientPreferenceModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public List<ClientPreferenceModel> searchAllById(List<Long> preferenceIds) {
        return mapper.toModelList(repository.findAllById(preferenceIds));
    }

    @Override
    public Optional<ClientPreferenceModel> update(Long id, ClientPreferenceModel obj) {
        var entity = mapper.toEntity(obj);
        return repository.findById(id).map(existing -> {
            mapper.patchEntity(existing, entity);
            var updated = repository.save(existing);
            return mapper.toModel(updated);
        });
    }

    @Override
    public boolean delete(Long id) {
        return repository.findById(id).map(e -> {
            repository.delete(e);
            return true;
        }).orElse(false);
    }

    @Override
    public List<ClientPreferenceModel> searchByClient(Long clientId) {
        List<ClientPreferenceEntity> listRecommendationEntity = repository.findByClientId(clientId);
        return mapper.toModelList(listRecommendationEntity);
    }

}