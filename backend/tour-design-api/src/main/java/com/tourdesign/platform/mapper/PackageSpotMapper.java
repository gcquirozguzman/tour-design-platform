package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.PackageSpotEntity;
import com.tourdesign.platform.model.PackageSpotModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface PackageSpotMapper {

    PackageSpotEntity toEntity(PackageSpotModel model);

    PackageSpotModel toModel(PackageSpotEntity entity);

    List<PackageSpotModel> toModelList(List<PackageSpotEntity> entities);

    void updateModelFromDto(PackageSpotModel model, @MappingTarget PackageSpotEntity entity);

    @InheritConfiguration
    PackageSpotEntity patchEntity(@MappingTarget PackageSpotEntity entity, PackageSpotEntity request);
}
