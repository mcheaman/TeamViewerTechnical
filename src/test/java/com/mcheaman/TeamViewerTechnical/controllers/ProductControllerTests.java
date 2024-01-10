package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.requests.ProductRequest;
import com.mcheaman.TeamViewerTechnical.services.ProductService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static ProductRequest productRequest;
    private static ProductModel product;
    @BeforeAll
    static void setup(){
        productRequest = new ProductRequest("test product", "product for use in testing");
        product = new ProductModel();
        product.setName("test product");
        product.setDescription("product for use in testing");
    }
    @Test
    public void testAddProduct() throws Exception{
        Mockito.when(productService.addProduct(productRequest)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n" + "  \"name\": \"test product\",\r\n" + " \"description\": \"product for use in testing\"\r\n" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetProducts() throws Exception{
        ProductModel product1 = new ProductModel();
        ProductModel product2 = new ProductModel();
        ProductModel product3 = new ProductModel();
        List<ProductModel> products = Arrays.asList(product1, product2, product3);
        Mockito.when(productService.getProducts()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products"))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateProduct() throws Exception{
        Mockito.when(productService.updateProduct(1L, productRequest)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" + "  \"name\": \"test product update\",\r\n" + " \"description\": \"product for use in testing that has been updated\"\r\n" + "}"))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testDeleteProduct() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
