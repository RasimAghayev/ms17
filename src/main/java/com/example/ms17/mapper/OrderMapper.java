package com.example.ms17.mapper;


import com.example.ms17.dto.OrderDto;
import com.example.ms17.model.onetomany.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    //    @Mappings({
//            @Mapping(target = "name", source = "clientName"),
    @Mapping(target = "orderNumber", source = "orderNumber")
//    })
//    @InheritConfiguration
    Order orderDtoToOrder(OrderDto orderDto);

    //    @Mappings({
//            @Mapping(target = "clientName", source = "name"),
    @Mapping(target = "orderNumber", source = "orderNumber")
//    })
    OrderDto orderToOrderDTO(Order order);
}