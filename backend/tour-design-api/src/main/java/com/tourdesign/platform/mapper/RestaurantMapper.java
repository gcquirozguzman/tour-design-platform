package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.RestaurantEntity;
import com.tourdesign.platform.model.RestaurantModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    RestaurantEntity toEntity(RestaurantModel model);

    RestaurantModel toModel(RestaurantEntity entity);

    List<RestaurantModel> toModelList(List<RestaurantEntity> entities);

    void updateModelFromDto(RestaurantModel model, @MappingTarget RestaurantEntity entity);

    @InheritConfiguration
    RestaurantEntity patchEntity(@MappingTarget RestaurantEntity entity, RestaurantEntity request);
}
