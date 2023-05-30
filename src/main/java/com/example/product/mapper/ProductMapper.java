package com.example.product.mapper;

import com.example.product.dto.ProductRequestDTO;
import com.example.product.dto.ProductResponceDTO;
import com.example.product.entity.ProductEntity;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Data
@Mapper
@Component
public class ProductMapper {

    public ProductResponceDTO productResponseMapper(ProductEntity entity){
        ProductResponceDTO dto = ProductResponceDTO.builder().price(entity.getPrice())
                .name(entity.getName()).quantity(entity.getQuantity()).build();
        return dto;

    }
    public ProductEntity productRequestMapper(ProductRequestDTO dto){
        ProductEntity entity = ProductEntity.builder().price(dto.getPrice()).
                quantity(dto.getQuantity()).cost(dto.getCost()).name(dto.getName()).build();
        return entity;

    }
}
