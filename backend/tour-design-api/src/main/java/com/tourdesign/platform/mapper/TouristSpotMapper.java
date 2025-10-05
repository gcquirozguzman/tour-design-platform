package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.TouristSpotEntity;
import com.tourdesign.platform.model.TouristSpotModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface TouristSpotMapper {

    TouristSpotEntity toEntity(TouristSpotModel model);

    TouristSpotModel toModel(TouristSpotEntity entity);

    List<TouristSpotModel> toModelList(List<TouristSpotEntity> entities);

    void updateModelFromDto(TouristSpotModel model, @MappingTarget TouristSpotEntity entity);

    @InheritConfiguration
    TouristSpotEntity patchEntity(@MappingTarget TouristSpotEntity entity, TouristSpotEntity request);
}
