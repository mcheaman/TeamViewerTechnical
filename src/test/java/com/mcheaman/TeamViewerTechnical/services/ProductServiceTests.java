package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.repositories.ProductRepository;
import com.mcheaman.TeamViewerTechnical.requests.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setup(){
        Optional<ProductModel> product_optional = Optional.of(new ProductModel(1L, "test product", "product for use in testing"));
        Mockito.when(productRepository.findById(1L)).thenReturn(product_optional);
    }
    @Test
    public void testGetProductById_Success() {
        String productName = "test product";
        ProductModel productById = productService.getProductById(1L);
        assertEquals(productName, productById.getName());
    }
    @Test
    public void testGetProductById_Failure() {
        try{
            productService.getProductById(2L);
            fail("404 NOT_FOUND not returned when expected");
        } catch (ResponseStatusException e){
            assertEquals("404 NOT_FOUND", e.getMessage());
        }
    }

    @Test
    public void testAddProduct() {
        ProductRequest productRequest = new ProductRequest("test product", "product for use in testing");
        ProductModel product = new ProductModel(1L, "test product", "product for use in testing");
        Mockito.when(productRepository.save(Mockito.any(ProductModel.class))).thenReturn(product);
        ProductModel newProduct = productService.addProduct(productRequest);
        assertEquals(newProduct.getId(), 1L);
    }
    @Test
    public void testDeleteProduct_Success() {
        try {
            productService.deleteProduct(1L);
        } catch (ResponseStatusException e){
            fail("Product that was attempted to delete was not present");
        }
    }

    @Test
    public void testDeleteProduct_Failure() {
        try {
            productService.deleteProduct(2L);
            fail("No error thrown when attempting to delete a non existent product");
        } catch (ResponseStatusException e){
            assertEquals(e.getMessage(), "404 NOT_FOUND");
        }
    }


}
