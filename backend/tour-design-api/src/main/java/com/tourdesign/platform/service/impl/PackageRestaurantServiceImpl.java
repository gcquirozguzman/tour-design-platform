package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.PackageRestaurantEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.PackageRestaurantModel;
import com.tourdesign.platform.mapper.PackageRestaurantMapper;
import com.tourdesign.platform.repository.PackageRestaurantRepository;
import com.tourdesign.platform.service.PackageRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PackageRestaurantServiceImpl implements PackageRestaurantService {

    @Autowired
    private PackageRestaurantRepository repository;

    @Autowired
    private PackageRestaurantMapper mapper;

    @Override
    public List<PackageRestaurantModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public PackageRestaurantModel create(PackageRestaurantModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<PackageRestaurantModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<PackageRestaurantModel> update(Long id, PackageRestaurantModel obj) {
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
