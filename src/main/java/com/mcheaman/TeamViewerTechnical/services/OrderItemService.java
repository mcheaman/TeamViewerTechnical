package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.repositories.OrderItemRepository;
import com.mcheaman.TeamViewerTechnical.repositories.OrderRepository;
import com.mcheaman.TeamViewerTechnical.repositories.ProductRepository;
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
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    /**
     * Get all order items present in the database
     * @return List<OrderItemModel> list of all order items
     */
    public List<OrderItemModel> getOrderItems() {
        return orderItemRepository.findAll();
    }
    /**
     * Get an order item by specifying the id
     * @return found OrderItemModel
     */
    public OrderItemModel getOrderItemById(Long id) {
        Optional<OrderItemModel> orderItem = orderItemRepository.findById(id);
        if(orderItem.isPresent()){
            return orderItem.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add a new order item, only if the order and product requested exist
     * @return saved OrderItemModel
     */
    public OrderItemModel addOrderItem(OrderItemRequest orderItemRequest){
        OrderItemModel orderItem = new OrderItemModel();
        Optional<OrderModel> order = orderRepository.findById(orderItemRequest.getOrderId());
        Optional<ProductModel> product = productRepository.findById(orderItemRequest.getProductId());
        if(order.isPresent() & product.isPresent()){
            orderItem.setOrderId(orderItemRequest.getOrderId());
            orderItem.setProductId(orderItemRequest.getProductId());
            return orderItemRepository.save(orderItem);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Update an order item by specifying id and providing new data
     * @return updated OrderItemModel
     */
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

    /**
     * Delete an order item by specifying id
     */
    public void deleteOrderItem(Long id) {
        Optional<OrderItemModel> orderItem = orderItemRepository.findById(id);
        if(orderItem.isPresent()){
            orderItemRepository.delete(orderItem.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
