package com.github.bkwak.pizzaapp.web;
import javax.validation.Valid;

import com.github.bkwak.pizzaapp.data.OrderRepository;
import com.github.bkwak.pizzaapp.model.PizzaOrder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
public class OrderController {
  
  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }
  
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid PizzaOrder order, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    
    orderRepo.save(order);
    sessionStatus.setComplete();
    
    return "redirect:/";
  }

}
