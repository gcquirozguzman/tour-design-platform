package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.RestaurantEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.RestaurantModel;
import com.tourdesign.platform.mapper.RestaurantMapper;
import com.tourdesign.platform.repository.RestaurantRepository;
import com.tourdesign.platform.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RestaurantMapper mapper;

    @Override
    public List<RestaurantModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public RestaurantModel create(RestaurantModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<RestaurantModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<RestaurantModel> update(Long id, RestaurantModel obj) {
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
