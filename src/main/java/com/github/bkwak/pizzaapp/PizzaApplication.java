package com.github.bkwak.pizzaapp;

import com.github.bkwak.pizzaapp.data.IngredientRepository;
import com.github.bkwak.pizzaapp.model.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaApplication {

  public static void main(String[] args) {
    SpringApplication.run(PizzaApplication.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo) {
    return args -> {
      repo.deleteAll();
      repo.save(new Ingredient("SMAL", "Small", Ingredient.Type.SIZE));
      repo.save(new Ingredient("MEDI", "Medium", Ingredient.Type.SIZE));
      repo.save(new Ingredient("LARG", "Large", Ingredient.Type.SIZE));

      repo.save(new Ingredient("CHIC", "Chicken", Ingredient.Type.PROTEIN));
      repo.save(new Ingredient("PEPP", "Pepperoni", Ingredient.Type.PROTEIN));
      repo.save(new Ingredient("PORK", "Pork", Ingredient.Type.PROTEIN));
      repo.save(new Ingredient("HAMM", "Ham", Ingredient.Type.PROTEIN));

      repo.save(new Ingredient("COUR", "Courgette", Ingredient.Type.VEGGIES));
      repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
      repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
      repo.save(new Ingredient("OLIV", "Olives", Ingredient.Type.VEGGIES));

      repo.save(new Ingredient("MOZA", "Mozarella", Ingredient.Type.CHEESE));
      repo.save(new Ingredient("FETA", "Feta", Ingredient.Type.CHEESE));
      repo.save(new Ingredient("GORG", "Gorgonzolla", Ingredient.Type.CHEESE));
      repo.save(new Ingredient("PARM", "Parmezan", Ingredient.Type.CHEESE));

      repo.save(new Ingredient("TMTS", "Tomato sauce", Ingredient.Type.SAUCE));
      repo.save(new Ingredient("GARL", "Garlic Sauce", Ingredient.Type.SAUCE));
      repo.save(new Ingredient("BBQS", "BBQ sauce", Ingredient.Type.SAUCE));
      repo.save(new Ingredient("CRSA", "Cream Sauce", Ingredient.Type.SAUCE));
    };
  }

}
