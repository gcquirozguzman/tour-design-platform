package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.RecommendationHistoryEntity;
import com.tourdesign.platform.model.RecommendationHistoryModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface RecommendationHistoryMapper {

    RecommendationHistoryEntity toEntity(RecommendationHistoryModel model);

    RecommendationHistoryModel toModel(RecommendationHistoryEntity entity);

    List<RecommendationHistoryModel> toModelList(List<RecommendationHistoryEntity> entities);

    void updateModelFromDto(RecommendationHistoryModel model, @MappingTarget RecommendationHistoryEntity entity);

    @InheritConfiguration
    RecommendationHistoryEntity patchEntity(@MappingTarget RecommendationHistoryEntity entity, RecommendationHistoryEntity request);
}
