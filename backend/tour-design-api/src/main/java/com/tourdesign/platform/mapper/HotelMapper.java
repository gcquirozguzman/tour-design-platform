package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.HotelEntity;
import com.tourdesign.platform.model.HotelModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface HotelMapper {

    HotelEntity toEntity(HotelModel model);

    HotelModel toModel(HotelEntity entity);

    List<HotelModel> toModelList(List<HotelEntity> entities);

    void updateModelFromDto(HotelModel model, @MappingTarget HotelEntity entity);

    @InheritConfiguration
    HotelEntity patchEntity(@MappingTarget HotelEntity entity, HotelEntity request);
}
