package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import com.mcheaman.TeamViewerTechnical.repositories.OrderItemRepository;
import com.mcheaman.TeamViewerTechnical.requests.OrderItemRequest;
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
public class OrderItemServiceTests {

    @Autowired
    private OrderItemService orderItemService;

    @MockBean
    private OrderItemRepository orderItemRepository;


    @BeforeEach
    void setup(){
        Optional<OrderItemModel> orderItem_optional = Optional.of(new OrderItemModel(1L, 2L, 3L ));
        Mockito.when(orderItemRepository.findById(1L)).thenReturn(orderItem_optional);
    }
    @Test
    public void testGetOrderItemById_Success() {
        Long orderId = 2L;
        Long productId = 3L;
        OrderItemModel orderItemById = orderItemService.getOrderItemById(1L);
        assertEquals(orderId, orderItemById.getOrderId());
        assertEquals(productId, orderItemById.getProductId());
    }
    @Test
    public void testGetOrderItemById_Failure() {
        try{
            orderItemService.getOrderItemById(2L);
            fail("404 NOT_FOUND not returned when expected");
        } catch (ResponseStatusException e){
            assertEquals("404 NOT_FOUND", e.getMessage());
        }
    }

    @Test
    public void testAddOrderItem() {
        OrderItemRequest orderItemRequest = new OrderItemRequest(4L, 12L);
        OrderItemModel orderItem = new OrderItemModel(2L, 4L, 12L);
        Mockito.when(orderItemRepository.save(Mockito.any(OrderItemModel.class))).thenReturn(orderItem);
        OrderItemModel newOrderItem = orderItemService.addOrderItem(orderItemRequest);
        assertEquals(newOrderItem.getOrderItemId(), 2L);
    }
    @Test
    public void testDeleteOrderItem_Success() {
        try {
            orderItemService.deleteOrderItem(1L);
        } catch (ResponseStatusException e){
            fail("OrderItem that was attempted to delete was not present");
        }
    }

    @Test
    public void testDeleteOrderItem_Failure() {
        try {
            orderItemService.deleteOrderItem(200L);
            fail("No error thrown when attempting to delete a non existent orderItem");
        } catch (ResponseStatusException e){
            assertEquals(e.getMessage(), "404 NOT_FOUND");
        }
    }


}
