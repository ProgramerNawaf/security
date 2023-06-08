package com.example.springsecurityy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {


    private Integer productId;
    private Integer quantity;

}
