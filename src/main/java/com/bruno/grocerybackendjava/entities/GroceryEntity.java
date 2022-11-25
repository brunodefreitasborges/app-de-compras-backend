package com.bruno.grocerybackendjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryEntity {

    @NotBlank(message = "Category is mandatory")
    private String category;
    @NotBlank(message = "Product Name is mandatory")
    private String product;
    private Double price;
    private Integer quantity;
    @Builder.Default
    private Boolean checked = false;
}
