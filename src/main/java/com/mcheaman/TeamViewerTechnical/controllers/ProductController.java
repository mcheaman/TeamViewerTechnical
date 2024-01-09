package com.mcheaman.TeamViewerTechnical.controllers;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.requests.ProductRequest;
import com.mcheaman.TeamViewerTechnical.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductModel> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("{id}")
    public ProductModel getProductByID(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public ProductModel addProduct(@RequestBody ProductRequest request){
        return productService.addProduct(request);
    }

    @PutMapping("{id}")
    public ProductModel updateProduct(@PathVariable Long id, @RequestBody ProductRequest request){
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
