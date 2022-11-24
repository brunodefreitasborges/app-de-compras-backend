package com.bruno.grocerybackendjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryEntity {

    @Id
    private String id;
    private String category;
    private String product;
    private Double price;
    private Integer quantity;
}
