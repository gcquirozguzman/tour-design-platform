package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.mapper.*;
import com.tourdesign.platform.model.TravelPackageModel;
import com.tourdesign.platform.repository.*;
import com.tourdesign.platform.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private TravelPackageMapper travelPackageMapper;

    @Autowired
    private PackageHotelRepository packageHotelRepository;

    @Autowired
    private PackageHotelMapper packageHotelMapper;

    @Override
    public List<TravelPackageModel> list() {
        return travelPackageMapper.toModelList(travelPackageRepository.findAll());
    }

    @Override
    public TravelPackageModel create(TravelPackageModel obj) {
        var entity = travelPackageMapper.toEntity(obj);
        entity = travelPackageRepository.save(entity);
        return travelPackageMapper.toModel(entity);
    }

    @Override
    public Optional<TravelPackageModel> search(Long id) {
        var entity = travelPackageRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(travelPackageMapper.toModel(entity));
    }

    @Override
    public Optional<TravelPackageModel> searchDetail(Long id) {
        var entity = travelPackageRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        TravelPackageModel travelPackageModel = travelPackageMapper.toModel(entity);

        return Optional.of(travelPackageMapper.toModel(entity));
    }

    @Override
    public Optional<TravelPackageModel> update(Long id, TravelPackageModel obj) {
        var entity = travelPackageMapper.toEntity(obj);
        return travelPackageRepository.findById(id).map(existing -> {
            travelPackageMapper.patchEntity(existing, entity);
            var updated = travelPackageRepository.save(existing);
            return travelPackageMapper.toModel(updated);
        });
    }

    @Override
    public boolean delete(Long id) {
        return travelPackageRepository.findById(id).map(e -> {
            travelPackageRepository.delete(e);
            return true;
        }).orElse(false);
    }
}
