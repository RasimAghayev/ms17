package com.example.ms17.mapper;


import com.example.ms17.dto.CustomerDto;
import com.example.ms17.model.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target="name", source="customerName"),
            @Mapping(target="surname", source="customerSurname"),
            @Mapping(target="address", source="customerAddress"),
            @Mapping(target="branch", source="customerBranch")
    })
        @InheritConfiguration
    Customer customerDtoToCustomer(CustomerDto customerDto);

    @Mappings({
            @Mapping(target="customerName", source="name"),
            @Mapping(target="customerSurname", source="surname"),
            @Mapping(target="customerAddress", source="address"),
            @Mapping(target="customerBranch", source="branch")
    })
    CustomerDto customerToCustomerDTO(Customer customer);
}