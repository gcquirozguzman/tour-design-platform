package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.HotelEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.HotelModel;
import com.tourdesign.platform.mapper.HotelMapper;
import com.tourdesign.platform.repository.HotelRepository;
import com.tourdesign.platform.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelMapper mapper;

    @Override
    public List<HotelModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public HotelModel create(HotelModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<HotelModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<HotelModel> update(Long id, HotelModel obj) {
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
