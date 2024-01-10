package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import com.mcheaman.TeamViewerTechnical.repositories.OrderItemRepository;
import com.mcheaman.TeamViewerTechnical.requests.OrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItemModel> getOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItemModel getOrderItemById(Long id) {
        Optional<OrderItemModel> orderItem = orderItemRepository.findById(id);
        if(orderItem.isPresent()){
            return orderItem.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public OrderItemModel addOrderItem(OrderItemRequest orderItemRequest){
        OrderItemModel orderItem = new OrderItemModel();
        orderItem.setOrderId(orderItemRequest.getOrderId());
        orderItem.setProductId(orderItemRequest.getProductId());

        return orderItemRepository.save(orderItem);
    }

    public OrderItemModel updateOrderItem(Long id, OrderItemRequest orderItemRequest){
        Optional<OrderItemModel> order_item_fetch = orderItemRepository.findById(id);
        if(order_item_fetch.isPresent()){
            OrderItemModel orderItem = order_item_fetch.get();
            orderItem.setOrderId(orderItemRequest.getOrderId());
            orderItem.setProductId(orderItemRequest.getProductId());
            return orderItemRepository.save(orderItem);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public int deleteOrderItem(Long id) {
        Optional<OrderItemModel> orderItem = orderItemRepository.findById(id);
        if(orderItem.isPresent()){
            orderItemRepository.delete(orderItem.get());
            return 1;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
