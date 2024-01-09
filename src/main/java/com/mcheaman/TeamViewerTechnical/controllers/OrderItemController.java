package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import com.mcheaman.TeamViewerTechnical.requests.OrderItemRequest;
import com.mcheaman.TeamViewerTechnical.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping()
    public List<OrderItemModel> getOrderItems(){
        return orderItemService.getOrderItems();
    }
    @GetMapping("{id}")
    public OrderItemModel getOrderItemByID(@PathVariable Long id){
        return orderItemService.getOrderItemById(id);
    }
    @PostMapping
    public OrderItemModel addOrderItem(@RequestBody OrderItemRequest request){
        return orderItemService.addOrderItem(request);
    }

    @PutMapping("{id}")
    public OrderItemModel updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest request){
        return orderItemService.updateOrderItem(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
    }
}
