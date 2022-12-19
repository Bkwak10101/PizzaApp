package com.github.bkwak.pizzaapp.data;

import com.github.bkwak.pizzaapp.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
