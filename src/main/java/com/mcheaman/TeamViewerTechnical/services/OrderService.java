package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.repositories.OrderRepository;
import com.mcheaman.TeamViewerTechnical.requests.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    /**
     * Get all orders present in the database
     * @return List<OrderModel> list of all orders
     */
    public List<OrderModel> getOrders() {
        return orderRepository.findAll();
    }
    /**
     * Get an order by specifying the id
     * @return found OrderModel
     */
    public OrderModel getOrderById(Long id) {
        Optional<OrderModel> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Add a new order
     * @return saved OrderModel
     */
    public OrderModel addOrder(OrderRequest orderRequest){
        OrderModel order = new OrderModel();
        order.setAccount(orderRequest.getAccount());
        order.setOrderTime(orderRequest.getOrderTime());

        return orderRepository.save(order);
    }
    /**
     * Update an order by specifying id and providing new data
     * @return updated OrderModel
     */
    public OrderModel updateOrder(Long id, OrderRequest orderRequest){
        Optional<OrderModel> order_fetch = orderRepository.findById(id);
        if(order_fetch.isPresent()){
            OrderModel order = order_fetch.get();
            order.setAccount(orderRequest.getAccount());
            order.setOrderTime(orderRequest.getOrderTime());
            return orderRepository.save(order);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete an order by specifying id
     */
    public void deleteOrder(Long id) {
        Optional<OrderModel> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.delete(order.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
