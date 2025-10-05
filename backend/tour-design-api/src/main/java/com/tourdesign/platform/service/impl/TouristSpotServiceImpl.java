package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.TouristSpotEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.TouristSpotModel;
import com.tourdesign.platform.mapper.TouristSpotMapper;
import com.tourdesign.platform.repository.TouristSpotRepository;
import com.tourdesign.platform.service.TouristSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TouristSpotServiceImpl implements TouristSpotService {

    @Autowired
    private TouristSpotRepository repository;

    @Autowired
    private TouristSpotMapper mapper;

    @Override
    public List<TouristSpotModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public TouristSpotModel create(TouristSpotModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<TouristSpotModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<TouristSpotModel> update(Long id, TouristSpotModel obj) {
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
