package com.github.bkwak.pizzaapp.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.github.bkwak.pizzaapp.model.Ingredient;
import com.github.bkwak.pizzaapp.model.Pizza;
import com.github.bkwak.pizzaapp.model.PizzaOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
public class OrderRepositoryTests {

  @Autowired
  OrderRepository orderRepo;
  
  @Test
  public void saveOrderWithTwoTacos() {
    PizzaOrder order = new PizzaOrder();
    order.setDeliveryName("Test McTest");
    order.setDeliveryStreet("1234 Test Lane");
    order.setDeliveryCity("Testville");
    order.setDeliveryState("CO");
    order.setDeliveryZip("80123");
    order.setCcNumber("4111111111111111");
    order.setCcExpiration("10/23");
    order.setCcCVV("123");
    Pizza taco1 = new Pizza();
    taco1.setName("Pizza One");
    taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
    taco1.addIngredient(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
    taco1.addIngredient(new Ingredient("CHED", "Shredded Cheddar", Ingredient.Type.CHEESE));
    order.addTaco(taco1);
    Pizza taco2 = new Pizza();
    taco2.setName("Pizza Two");
    taco2.addIngredient(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
    taco2.addIngredient(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
    taco2.addIngredient(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
    order.addTaco(taco2);
    
    PizzaOrder savedOrder = orderRepo.save(order);
    assertThat(savedOrder.getId()).isNotNull();
        
    PizzaOrder fetchedOrder = orderRepo.findById(savedOrder.getId()).get();
    assertThat(fetchedOrder.getDeliveryName()).isEqualTo("Test McTest");
    assertThat(fetchedOrder.getDeliveryStreet()).isEqualTo("1234 Test Lane");
    assertThat(fetchedOrder.getDeliveryCity()).isEqualTo("Testville");
    assertThat(fetchedOrder.getDeliveryState()).isEqualTo("CO");
    assertThat(fetchedOrder.getDeliveryZip()).isEqualTo("80123");
    assertThat(fetchedOrder.getCcNumber()).isEqualTo("4111111111111111");
    assertThat(fetchedOrder.getCcExpiration()).isEqualTo("10/23");
    assertThat(fetchedOrder.getCcCVV()).isEqualTo("123");
    assertThat(fetchedOrder.getPlacedAt().getTime()).isEqualTo(savedOrder.getPlacedAt().getTime());
    List<Pizza> tacos = fetchedOrder.getTacos();
    assertThat(tacos.size()).isEqualTo(2);
    assertThat(tacos).containsExactlyInAnyOrder(taco1, taco2);
  }
  
}
