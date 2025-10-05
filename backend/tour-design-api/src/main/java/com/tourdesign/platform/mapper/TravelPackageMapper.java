package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.TravelPackageEntity;
import com.tourdesign.platform.model.TravelPackageModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface TravelPackageMapper {

    TravelPackageEntity toEntity(TravelPackageModel model);

    TravelPackageModel toModel(TravelPackageEntity entity);

    List<TravelPackageModel> toModelList(List<TravelPackageEntity> entities);

    void updateModelFromDto(TravelPackageModel model, @MappingTarget TravelPackageEntity entity);

    @InheritConfiguration
    TravelPackageEntity patchEntity(@MappingTarget TravelPackageEntity entity, TravelPackageEntity request);
}
