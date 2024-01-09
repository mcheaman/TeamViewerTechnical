package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.requests.OrderRequest;
import com.mcheaman.TeamViewerTechnical.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public List<OrderModel> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("{id}")
    public OrderModel getOrderByID(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @PostMapping
    public OrderModel addOrderItem(@RequestBody OrderRequest request){
        return orderService.addOrder(request);
    }

    @PutMapping("{id}")
    public OrderModel updateOrder(@PathVariable Long id, @RequestBody OrderRequest request){
        return orderService.updateOrder(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
