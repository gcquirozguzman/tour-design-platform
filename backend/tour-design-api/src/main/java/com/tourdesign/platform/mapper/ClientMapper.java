package com.tourdesign.platform.mapper;

import com.tourdesign.platform.entity.ClientEntity;
import com.tourdesign.platform.model.ClientModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ClientMapper {

    ClientEntity toEntity(ClientModel model);

    ClientModel toModel(ClientEntity entity);

    List<ClientModel> toModelList(List<ClientEntity> entities);

    void updateModelFromDto(ClientModel model, @MappingTarget ClientEntity entity);

    @InheritConfiguration
    ClientEntity patchEntity(@MappingTarget ClientEntity entity, ClientEntity request);

}