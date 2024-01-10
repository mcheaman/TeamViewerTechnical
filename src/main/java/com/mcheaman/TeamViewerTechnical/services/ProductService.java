package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.repositories.ProductRepository;
import com.mcheaman.TeamViewerTechnical.requests.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductModel> getProducts() {
        return productRepository.findAll();
    }

    public ProductModel getProductById(Long id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ProductModel addProduct(ProductRequest productRequest){
        ProductModel product = new ProductModel();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());

        return productRepository.save(product);
    }

    public ProductModel updateProduct(Long id, ProductRequest productRequest){
        Optional<ProductModel> product_fetch = productRepository.findById(id);
        if(product_fetch.isPresent()){
            ProductModel product = product_fetch.get();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            return productRepository.save(product);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public void deleteProduct(Long id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
