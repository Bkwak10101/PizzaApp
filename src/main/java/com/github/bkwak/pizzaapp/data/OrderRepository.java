package com.github.bkwak.pizzaapp.data;

import com.github.bkwak.pizzaapp.model.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository 
         extends CrudRepository<PizzaOrder, Long> {

}
