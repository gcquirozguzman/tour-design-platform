package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.TravelPackageEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.TravelPackageModel;
import com.tourdesign.platform.mapper.TravelPackageMapper;
import com.tourdesign.platform.repository.TravelPackageRepository;
import com.tourdesign.platform.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired
    private TravelPackageRepository repository;

    @Autowired
    private TravelPackageMapper mapper;

    @Override
    public List<TravelPackageModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public TravelPackageModel create(TravelPackageModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<TravelPackageModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<TravelPackageModel> update(Long id, TravelPackageModel obj) {
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
}
