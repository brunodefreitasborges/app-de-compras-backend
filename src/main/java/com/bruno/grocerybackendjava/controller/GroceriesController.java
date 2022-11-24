package com.bruno.grocerybackendjava.controller;

import com.bruno.grocerybackendjava.entities.GroceryEntity;
import com.bruno.grocerybackendjava.entities.GroceryResponse;
import com.bruno.grocerybackendjava.service.GroceriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groceries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GroceriesController {

    private final GroceriesService groceriesService;

    @GetMapping("")
    public List<GroceryResponse> getAllGroceries() {
        return groceriesService.getAllGroceries();
    }

    @PostMapping("")
    public GroceryEntity addGrocery(@RequestBody GroceryEntity grocery) {
        return groceriesService.addGrocery(grocery);
    }

    @PutMapping("/{id}")
    public GroceryEntity updateGrocery(@PathVariable String id, @RequestBody GroceryEntity grocery) {
        return groceriesService.updateGrocery(id, grocery);
    }

    @DeleteMapping("/{id}")
    public void deleteGrocery(@PathVariable String id) {
        groceriesService.deleteGrocery(id);
    }


}
