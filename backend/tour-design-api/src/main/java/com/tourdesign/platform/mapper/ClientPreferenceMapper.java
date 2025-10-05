package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.ClientPreferenceEntity;
import com.tourdesign.platform.model.ClientPreferenceModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ClientPreferenceMapper {

    ClientPreferenceEntity toEntity(ClientPreferenceModel model);

    ClientPreferenceModel toModel(ClientPreferenceEntity entity);

    List<ClientPreferenceModel> toModelList(List<ClientPreferenceEntity> entities);

    void updateModelFromDto(ClientPreferenceModel model, @MappingTarget ClientPreferenceEntity entity);

    @InheritConfiguration
    ClientPreferenceEntity patchEntity(@MappingTarget ClientPreferenceEntity entity, ClientPreferenceEntity request);
}
