package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.PackageHotelEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.PackageHotelModel;
import com.tourdesign.platform.mapper.PackageHotelMapper;
import com.tourdesign.platform.repository.PackageHotelRepository;
import com.tourdesign.platform.service.PackageHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PackageHotelServiceImpl implements PackageHotelService {

    @Autowired
    private PackageHotelRepository repository;

    @Autowired
    private PackageHotelMapper mapper;

    @Override
    public List<PackageHotelModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public PackageHotelModel create(PackageHotelModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<PackageHotelModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<PackageHotelModel> update(Long id, PackageHotelModel obj) {
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
