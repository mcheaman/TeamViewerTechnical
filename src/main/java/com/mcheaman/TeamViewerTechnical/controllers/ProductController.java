package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.requests.ProductRequest;
import com.mcheaman.TeamViewerTechnical.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Products", description = "Product management endpoints")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @Operation(summary = "Get a list of all products")
    @GetMapping()
    public List<ProductModel> getProducts(){
        return productService.getProducts();
    }
    @Operation(summary = "Get a product by ID")
    @GetMapping("{id}")
    public ProductModel getProductByID(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @Operation(
            summary = "Create a new product",
            description = "Add a new product by providing the name and description")
    @PostMapping
    public ProductModel addProduct(@RequestBody ProductRequest request){
        return productService.addProduct(request);
    }
    @Operation(
            summary = "Update an existing product",
            description = "Update a product by specifying the productId and providing new data")
    @PutMapping("{id}")
    public ProductModel updateProduct(@PathVariable Long id, @RequestBody ProductRequest request){
        return productService.updateProduct(id, request);
    }
    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
