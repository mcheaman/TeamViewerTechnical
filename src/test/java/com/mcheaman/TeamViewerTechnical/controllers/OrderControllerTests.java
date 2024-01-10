package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import com.mcheaman.TeamViewerTechnical.requests.OrderRequest;
import com.mcheaman.TeamViewerTechnical.services.OrderService;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private static OrderRequest orderRequest;
    private static OrderModel order;
    private static LocalDateTime orderTime;

    @BeforeAll
    static void setup(){
        orderTime = LocalDateTime.now();
        orderRequest = new OrderRequest("test account", orderTime);
        order = new OrderModel();
        order.setAccount("test account");
        order.setOrderTime(orderTime);
    }

    @Test
    public void testAddOrder() throws Exception{
        Mockito.when(orderService.addOrder(orderRequest)).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" + "  \"account\": \"test account\",\r\n" + " \"orderTime\": \"" + orderTime.toString() + "\"\r\n" + "}"))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetOrders() throws Exception{
        OrderModel order1 = new OrderModel();
        OrderModel order2 = new OrderModel();
        OrderModel order3 = new OrderModel();
        List<OrderModel> orders = Arrays.asList(order1, order2, order3);
        Mockito.when(orderService.getOrders()).thenReturn(orders);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateOrder() throws Exception{
        Mockito.when(orderService.updateOrder(1L, orderRequest)).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n" + "  \"account\": \"updated test account\",\r\n" + " \"orderTime\": \"" + LocalDateTime.now().toString() + "\"\r\n" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testDeleteOrder() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
