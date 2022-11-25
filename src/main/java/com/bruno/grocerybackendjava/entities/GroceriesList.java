package com.bruno.grocerybackendjava.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "lists")
public class GroceriesList {

    @Id
    private String id;
    private String listName;
    @Builder.Default
    private List<GroceryEntity> groceryList = List.of();
}
