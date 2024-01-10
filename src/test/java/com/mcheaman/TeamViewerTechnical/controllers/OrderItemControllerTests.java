package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import com.mcheaman.TeamViewerTechnical.requests.OrderItemRequest;
import com.mcheaman.TeamViewerTechnical.services.OrderItemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(OrderItemController.class)
public class OrderItemControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderItemService orderItemService;

    private static OrderItemRequest orderItemRequest;
    private static OrderItemModel orderItem;

    @BeforeAll
    static void setup(){
        orderItemRequest = new OrderItemRequest(1L, 2L);
        orderItem = new OrderItemModel();
        orderItem.setOrderId(1L);
        orderItem.setProductId(2L);
    }

    @Test
    public void testAddOrderItem() throws Exception{
        Mockito.when(orderItemService.addOrderItem(orderItemRequest)).thenReturn(orderItem);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/order-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" + "  \"orderId\": 1,\r\n" + " \"productId\": 2\r\n" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetOrderItems() throws Exception{
        OrderItemModel orderItem1 = new OrderItemModel();
        OrderItemModel orderItem2 = new OrderItemModel();
        OrderItemModel orderItem3 = new OrderItemModel();
        List<OrderItemModel> orderItems = Arrays.asList(orderItem1, orderItem2, orderItem3);
        Mockito.when(orderItemService.getOrderItems()).thenReturn(orderItems);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/order-items"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateOrderItem() throws Exception{
        Mockito.when(orderItemService.updateOrderItem(1L, orderItemRequest)).thenReturn(orderItem);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/order-items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" + "  \"orderId\": 1,\r\n" + " \"productId\": 3\r\n" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testDeleteOrderItem() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/order-items/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
