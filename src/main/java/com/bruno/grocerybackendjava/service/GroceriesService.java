package com.bruno.grocerybackendjava.service;

import com.bruno.grocerybackendjava.entities.FilteredGroceries;
import com.bruno.grocerybackendjava.entities.GroceriesList;
import com.bruno.grocerybackendjava.entities.GroceriesListResponse;
import com.bruno.grocerybackendjava.entities.GroceryEntity;
import com.bruno.grocerybackendjava.repository.ListsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GroceriesService {

    private final ListsRepository listsRepository;

    public GroceriesList addList(GroceriesList list) {
        return listsRepository.save(list);
    }

    // Returns all Grocery Lists, filtered
    public List<GroceriesListResponse> getAllLists(String id) {
        List<GroceriesListResponse> groceriesListResponses = new ArrayList<>();
        List<GroceriesList> lists = listsRepository.findAll();

        lists.forEach(list -> {
            GroceriesListResponse groceriesListResponse = filterGroceryList(list);
            groceriesListResponses.add(groceriesListResponse);
        });

           return groceriesListResponses;
        }


    public GroceriesList updateList(String id, GroceriesList list) {
        GroceriesList listToUpdate = listsRepository.findById(id).orElseThrow(() -> new RuntimeException("List not found"));
        list.setId(listToUpdate.getId());
        if (list.getGroceryList().isEmpty()) {
            list.setGroceryList(listToUpdate.getGroceryList());
        }
        if (list.getListName().isEmpty()) {
            list.setListName(listToUpdate.getListName());
        }
        return listsRepository.save(list);
    }

    public void deleteList(String id) {
        listsRepository.deleteById(id);
    }

    // Adds a new Grocery to a Grocery List, if the Grocery already exists, it will throw an error
    public GroceriesListResponse addGroceryToList(String id, GroceryEntity grocery) {
        GroceriesList list = listsRepository.findById(id).orElseThrow(() -> new RuntimeException("List not found"));
        list.getGroceryList().stream().filter(groceryEntity -> {
            if (groceryEntity.getProduct().equals(grocery.getProduct())) {
                throw new RuntimeException("Product already exists");
            }
            return false;
        }).findFirst().orElseGet(() -> {
            list.getGroceryList().add(grocery);
            return grocery;
        });
        return filterGroceryList(listsRepository.save(list));
    }

    // Deletes a Grocery from a Grocery List, if the Grocery doesn't exist, it will throw an error
    public GroceriesList deleteGroceryFromList(String id, GroceryEntity grocery) {
        GroceriesList list = listsRepository.findById(id).orElseThrow(() -> new RuntimeException("List not found"));
        GroceryEntity productToDelete = list.getGroceryList().stream().filter(groceryEntity ->
                Objects.equals(groceryEntity.getProduct(), grocery.getProduct())
        ).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));

        list.getGroceryList().remove(productToDelete);
        return listsRepository.save(list);
    }

    public GroceriesListResponse updateGroceryFromList(String id, GroceryEntity grocery) {
        GroceriesList list = listsRepository.findById(id).orElseThrow(() -> new RuntimeException("List not found"));
        GroceryEntity productToUpdate = list.getGroceryList().stream().filter(groceryEntity ->
                Objects.equals(groceryEntity.getProduct(), grocery.getProduct())
        ).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));

        if(grocery.getQuantity() != null) {
            productToUpdate.setQuantity(grocery.getQuantity());
        }
        if(grocery.getPrice() != null) {
            productToUpdate.setPrice(grocery.getPrice());
        }
        if(grocery.getChecked() != null) {
            productToUpdate.setChecked(grocery.getChecked());
        }

        productToUpdate.setCategory(grocery.getCategory());
        productToUpdate.setProduct(grocery.getProduct());

        return filterGroceryList(listsRepository.save(list));

    }

    // Method to Filter Groceries into Categories
    public GroceriesListResponse filterGroceryList(GroceriesList groceries) {
        List<String> categories = groceries.getGroceryList().stream().map(GroceryEntity::getCategory).distinct().toList();

        GroceriesListResponse groceriesListResponse = GroceriesListResponse.builder().groceryList(List.of(FilteredGroceries.builder().build())).build();

        List<FilteredGroceries> allFilteredGroceries = new ArrayList<>();

        categories.forEach(category -> {
            FilteredGroceries filteredGroceries = FilteredGroceries.builder().category(category).groceries(List.of()).build();
            List<GroceryEntity> groceryEntities = groceries.getGroceryList().stream().filter(groceryEntity ->
                    Objects.equals(groceryEntity.getCategory(), category)).toList();
        filteredGroceries.setGroceries(groceryEntities);
        allFilteredGroceries.add(filteredGroceries);
        });

        groceriesListResponse.setGroceryList(allFilteredGroceries);
        groceriesListResponse.setId(groceries.getId());
        groceriesListResponse.setListName(groceries.getListName());

        return groceriesListResponse;
    }
}





