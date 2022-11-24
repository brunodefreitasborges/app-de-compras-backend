package com.bruno.grocerybackendjava.service;

import com.bruno.grocerybackendjava.entities.GroceryEntity;
import com.bruno.grocerybackendjava.entities.GroceryResponse;
import com.bruno.grocerybackendjava.repository.GroceryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceriesService {

    private final GroceryRepository groceriesRepository;

    public List<GroceryResponse> getAllGroceries() {
        List<GroceryEntity> allGroceries = groceriesRepository.findAll();
        List<String> categories = allGroceries.stream()
                .map(GroceryEntity::getCategory).distinct().toList();

        return categories.stream().map(category -> {
            List<GroceryEntity> groceries = allGroceries.stream()
                    .filter(grocery -> grocery.getCategory().equals(category))
                    .toList();
            return GroceryResponse.builder().category(category).groceries(groceries).build();
        }).toList();
    }

    public GroceryEntity addGrocery(GroceryEntity grocery) {
        return groceriesRepository.save(grocery);
    }

    public GroceryEntity updateGrocery(String id, GroceryEntity grocery) {
        GroceryEntity groceryFound = groceriesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Grocery not found"));

        grocery.setId(groceryFound.getId());
        return groceriesRepository.save(grocery);
    }

    public void deleteGrocery(@PathVariable String id) {
        GroceryEntity groceryFound = groceriesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Grocery not found"));

        groceriesRepository.delete(groceryFound);
    }

}
