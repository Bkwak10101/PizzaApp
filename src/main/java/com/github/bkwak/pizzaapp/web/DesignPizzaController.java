package com.github.bkwak.pizzaapp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.github.bkwak.pizzaapp.model.Ingredient;
import com.github.bkwak.pizzaapp.data.IngredientRepository;
import com.github.bkwak.pizzaapp.model.Pizza;
import com.github.bkwak.pizzaapp.model.PizzaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public DesignPizzaController(
        IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Ingredient.Type[] types = Ingredient.Type.values();
    for (Ingredient.Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "pizzaOrder")
  public PizzaOrder order() {
    return new PizzaOrder();
  }

  @ModelAttribute(name = "pizza")
  public Pizza pizza() {
    return new Pizza();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processPizza(
          @Valid Pizza pizza, Errors errors,
          @ModelAttribute PizzaOrder pizzaOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

    pizzaOrder.addPizza(pizza);

    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(
      List<Ingredient> ingredients, Ingredient.Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}
