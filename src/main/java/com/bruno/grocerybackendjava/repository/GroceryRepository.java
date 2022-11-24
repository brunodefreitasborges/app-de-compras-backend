package com.bruno.grocerybackendjava.repository;

import com.bruno.grocerybackendjava.entities.GroceryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepository extends MongoRepository<GroceryEntity, String> {

}

