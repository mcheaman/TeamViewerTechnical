package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.repositories.OrderRepository;
import com.mcheaman.TeamViewerTechnical.requests.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    private static LocalDateTime orderTime;

    @BeforeEach
    void setup(){
        orderTime = LocalDateTime.now();
        Optional<OrderModel> order_optional = Optional.of(new OrderModel(1L, "test account", orderTime));
        Mockito.when(orderRepository.findById(1L)).thenReturn(order_optional);
    }
    @Test
    public void testGetOrderById_Success() {
        String accountName = "test account";
        OrderModel orderById = orderService.getOrderById(1L);
        assertEquals(accountName, orderById.getAccount());
        assertEquals(orderTime, orderById.getOrderTime());
    }
    @Test
    public void testGetOrderById_Failure() {
        try{
            orderService.getOrderById(2L);
            fail("404 NOT_FOUND not returned when expected");
        } catch (ResponseStatusException e){
            assertEquals("404 NOT_FOUND", e.getMessage());
        }
    }

    @Test
    public void testAddOrder() {
        OrderRequest orderRequest = new OrderRequest("test account", orderTime);
        OrderModel order = new OrderModel(2L, "test account", orderTime);
        Mockito.when(orderRepository.save(Mockito.any(OrderModel.class))).thenReturn(order);
        OrderModel newOrder = orderService.addOrder(orderRequest);
        assertEquals(newOrder.getOrderId(), 2L);
    }
    @Test
    public void testDeleteOrder_Success() {
        try {
            orderService.deleteOrder(1L);
        } catch (ResponseStatusException e){
            fail("Order that was attempted to delete was not present");
        }
    }

    @Test
    public void testDeleteOrder_Failure() {
        try {
            orderService.deleteOrder(2L);
            fail("No error thrown when attempting to delete a non existent order");
        } catch (ResponseStatusException e){
            assertEquals(e.getMessage(), "404 NOT_FOUND");
        }
    }


}
