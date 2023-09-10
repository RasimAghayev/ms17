package com.example.ms17.mapper;


import com.example.ms17.dto.CustomerDto;
import com.example.ms17.model.Customer;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @InheritConfiguration
    @Mapping(source = "customerName", target = "name")
    Customer customerDtoToCustomer(CustomerDto customerDto);

    //    @InheritConfiguration
    @Mapping(source = "name", target = "customerName")
    CustomerDto customerToCustomerDTO(Customer customer);
}