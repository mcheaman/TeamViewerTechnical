package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.repositories.OrderRepository;
import com.mcheaman.TeamViewerTechnical.requests.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderModel> getOrders() {
        return orderRepository.findAll();
    }

    public OrderModel getOrderById(Long id) {
        Optional<OrderModel> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        }else{
            //TODO: Throw HTTP error
            return null;
        }
    }

    public OrderModel addOrder(OrderRequest orderRequest){
        OrderModel order = new OrderModel();
        order.setAccount(orderRequest.getAccount());
        order.setOrderTime(orderRequest.getOrderTime());

        return orderRepository.save(order);
    }

    public OrderModel updateOrder(Long id, OrderRequest orderRequest){
        Optional<OrderModel> order_fetch = orderRepository.findById(id);
        if(order_fetch.isPresent()){
            OrderModel order = order_fetch.get();
            order.setAccount(orderRequest.getAccount());
            order.setOrderTime(orderRequest.getOrderTime());
            return orderRepository.save(order);
        }else{
            //TODO: Throw HTTP error
            return null;
        }
    }


    public int deleteOrder(Long id) {
        Optional<OrderModel> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.delete(order.get());
            return 1;
        }else{
            //TODO: Throw HTTP error
            return -1;
        }
    }

}