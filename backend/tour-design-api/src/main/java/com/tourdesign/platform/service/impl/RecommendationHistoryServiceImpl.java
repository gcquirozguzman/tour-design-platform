package com.tourdesign.platform.service.impl;

import com.tourdesign.platform.entity.RecommendationHistoryEntity;
import com.tourdesign.platform.exception.DataNotFoundException;
import com.tourdesign.platform.model.RecommendationHistoryModel;
import com.tourdesign.platform.mapper.RecommendationHistoryMapper;
import com.tourdesign.platform.repository.RecommendationHistoryRepository;
import com.tourdesign.platform.service.RecommendationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationHistoryServiceImpl implements RecommendationHistoryService {

    @Autowired
    private RecommendationHistoryRepository repository;

    @Autowired
    private RecommendationHistoryMapper mapper;

    @Override
    public List<RecommendationHistoryModel> list() {
        return mapper.toModelList(repository.findAll());
    }

    @Override
    public RecommendationHistoryModel create(RecommendationHistoryModel obj) {
        var entity = mapper.toEntity(obj);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Optional<RecommendationHistoryModel> search(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<RecommendationHistoryModel> update(Long id, RecommendationHistoryModel obj) {
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
