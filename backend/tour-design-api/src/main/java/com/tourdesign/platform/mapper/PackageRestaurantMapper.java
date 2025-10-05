package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.PackageRestaurantEntity;
import com.tourdesign.platform.model.PackageRestaurantModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface PackageRestaurantMapper {

    PackageRestaurantEntity toEntity(PackageRestaurantModel model);

    PackageRestaurantModel toModel(PackageRestaurantEntity entity);

    List<PackageRestaurantModel> toModelList(List<PackageRestaurantEntity> entities);

    void updateModelFromDto(PackageRestaurantModel model, @MappingTarget PackageRestaurantEntity entity);

    @InheritConfiguration
    PackageRestaurantEntity patchEntity(@MappingTarget PackageRestaurantEntity entity, PackageRestaurantEntity request);
}
