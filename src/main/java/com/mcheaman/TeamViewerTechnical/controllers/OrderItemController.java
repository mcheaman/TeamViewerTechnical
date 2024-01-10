package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import com.mcheaman.TeamViewerTechnical.requests.OrderItemRequest;
import com.mcheaman.TeamViewerTechnical.services.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Order items", description = "Order items management endpoints")
@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;
    @Operation(summary = "Get a list of all order items")
    @GetMapping()
    public List<OrderItemModel> getOrderItems(){
        return orderItemService.getOrderItems();
    }
    @Operation(summary = "Get an order item by ID")
    @GetMapping("{id}")
    public OrderItemModel getOrderItemByID(@PathVariable Long id){
        return orderItemService.getOrderItemById(id);
    }
    @Operation(
            summary = "Create a new order item",
            description = "Add a new order item by providing the orderId and productId")
    @PostMapping
    public OrderItemModel addOrderItem(@RequestBody OrderItemRequest request){
        return orderItemService.addOrderItem(request);
    }
    @Operation(
            summary = "Update an existing order item",
            description = "Update an order item by specifying the orderItemId and providing new data")
    @PutMapping("{id}")
    public OrderItemModel updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest request){
        return orderItemService.updateOrderItem(id, request);
    }
    @Operation(summary = "Delete an order item by ID")
    @DeleteMapping("{id}")
    public void deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
    }
}
