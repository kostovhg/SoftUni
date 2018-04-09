package AutoMappingExerciseApplication.controllers;

import AutoMappingExerciseApplication.services.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController extends BaseController{

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String buyItem() {
        return this.orderService.createOrder(userSession);
    }
}
