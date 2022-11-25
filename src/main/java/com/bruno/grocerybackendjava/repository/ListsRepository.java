package com.bruno.grocerybackendjava.repository;

import com.bruno.grocerybackendjava.entities.GroceriesList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListsRepository extends MongoRepository<GroceriesList, String> {

}

