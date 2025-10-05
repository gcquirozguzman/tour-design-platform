package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.PackageHotelEntity;
import com.tourdesign.platform.model.PackageHotelModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface PackageHotelMapper {

    PackageHotelEntity toEntity(PackageHotelModel model);

    PackageHotelModel toModel(PackageHotelEntity entity);

    List<PackageHotelModel> toModelList(List<PackageHotelEntity> entities);

    void updateModelFromDto(PackageHotelModel model, @MappingTarget PackageHotelEntity entity);

    @InheritConfiguration
    PackageHotelEntity patchEntity(@MappingTarget PackageHotelEntity entity, PackageHotelEntity request);
}
