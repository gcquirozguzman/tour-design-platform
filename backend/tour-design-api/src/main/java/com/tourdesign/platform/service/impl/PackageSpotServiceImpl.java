package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.PackageSpotModel;
import com.tourdesign.platform.mapper.PackageSpotMapper;
import com.tourdesign.platform.repository.PackageSpotRepository;
import com.tourdesign.platform.service.PackageSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PackageSpotServiceImpl implements PackageSpotService {

    @Autowired
    private PackageSpotRepository repository;

    @Autowired
    private PackageSpotMapper mapper;

    @Override
    public List<PackageSpotModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public PackageSpotModel create(PackageSpotModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<PackageSpotModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public List<PackageSpotModel> findByTravelPackageId(Long packageId) {
        return mapper.toModelList(repository.findByTravelPackageId(packageId));
    }

    @Override
    public Optional<PackageSpotModel> update(Long id, PackageSpotModel obj) {
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
