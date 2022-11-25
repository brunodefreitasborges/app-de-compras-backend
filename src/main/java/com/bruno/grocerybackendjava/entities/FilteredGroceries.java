package com.bruno.grocerybackendjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilteredGroceries {

    private String category;
    private List<GroceryEntity> groceries;

}
