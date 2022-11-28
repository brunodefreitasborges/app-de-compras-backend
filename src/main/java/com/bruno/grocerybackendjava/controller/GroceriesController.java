package com.bruno.grocerybackendjava.controller;

import com.bruno.grocerybackendjava.entities.FilteredGroceries;
import com.bruno.grocerybackendjava.entities.GroceriesList;
import com.bruno.grocerybackendjava.entities.GroceriesListResponse;
import com.bruno.grocerybackendjava.entities.GroceryEntity;
import com.bruno.grocerybackendjava.service.GroceriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class GroceriesController {

    private final GroceriesService groceriesService;

    // Lists Operations
    @PostMapping("")
    // Creates an Empty Grocery List
    public GroceriesList addList(@RequestBody GroceriesList list) {
        return groceriesService.addList(list);
    }

    @GetMapping("")
    public List<GroceriesListResponse> getLists(@RequestParam(value="id", required = false, defaultValue = "") String id) {
        return groceriesService.getAllLists(id);
    }

    @PutMapping("/{id}")
    public GroceriesList updateList(@PathVariable String id, @RequestBody GroceriesList list) {
        return groceriesService.updateList(id, list);
    }

    @DeleteMapping("/{list}")
    public void deleteList(@PathVariable String list) {
        groceriesService.deleteList(list);
    }

    // Groceries Operations
    @PostMapping("list/{id}")
    // Adds a Grocery to a Grocery List
    public GroceriesListResponse addGroceryToList(@PathVariable String id, @RequestBody GroceryEntity grocery) {
        return groceriesService.addGroceryToList(id, grocery);
    }

    @PutMapping("list/{id}")
    // Updates a Grocery from a Grocery List
    public GroceriesListResponse updateGroceryFromList(@PathVariable String id, @RequestBody GroceryEntity grocery) {
        return groceriesService.updateGroceryFromList(id, grocery);
    }

    @DeleteMapping("list/{id}")
    public GroceriesList deleteGroceryFromList(@PathVariable String id, @RequestBody GroceryEntity grocery) {
        return groceriesService.deleteGroceryFromList(id, grocery);
    }

}
