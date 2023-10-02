package com.example.ms17.mapper;


import com.example.ms17.dto.ClientDto;
import com.example.ms17.model.onetomany.Client;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "clientName"),
            @Mapping(target = "surname", source = "clientSurname"),
            @Mapping(target = "orders", source = "orders")
    })
    @InheritConfiguration
    Client clientDtoToClient(ClientDto clientDto);

    @Mappings({
            @Mapping(target = "clientName", source = "name"),
            @Mapping(target = "clientSurname", source = "surname"),
            @Mapping(target = "orders", source = "orders")
    })
    ClientDto clientToClientDTO(Client client);
}