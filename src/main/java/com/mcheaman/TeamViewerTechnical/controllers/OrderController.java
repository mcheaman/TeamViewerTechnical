package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.requests.OrderRequest;
import com.mcheaman.TeamViewerTechnical.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Orders", description = "Order management endpoints")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @Operation(summary = "Get a list of all orders")
    @GetMapping()
    public List<OrderModel> getOrders(){
        return orderService.getOrders();
    }
    @Operation(summary = "Get an order by ID")
    @GetMapping("{id}")
    public OrderModel getOrderByID(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @Operation(
            summary = "Create a new order",
            description = "Add a new order by providing the account and orderTime")
    @PostMapping
    public OrderModel addOrderItem(@RequestBody OrderRequest request){
        return orderService.addOrder(request);
    }
    @Operation(
            summary = "Update an existing order",
            description = "Update an order by specifying the orderId and providing new data")
    @PutMapping("{id}")
    public OrderModel updateOrder(@PathVariable Long id, @RequestBody OrderRequest request){
        return orderService.updateOrder(id, request);
    }
    @Operation(summary = "Delete an order by ID")
    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
