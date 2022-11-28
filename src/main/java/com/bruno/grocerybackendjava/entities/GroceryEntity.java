package com.bruno.grocerybackendjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryEntity {

    @NotBlank(message = "Category is mandatory")
    private String category;
    @NotBlank(message = "Product Name is mandatory")
    private String product;
    @Builder.Default
    private Double price = 0.00;
    private Integer quantity;
    @Builder.Default
    private Boolean checked = false;


}
