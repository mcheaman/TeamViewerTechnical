package com.mcheaman.TeamViewerTechnical.services;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import com.mcheaman.TeamViewerTechnical.repositories.ProductRepository;
import com.mcheaman.TeamViewerTechnical.requests.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            //TODO: Throw HTTP error
            return null;
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
            //TODO: Throw HTTP error
            return null;
        }
    }


    public int deleteProduct(Long id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return 1;
        }else{
            //TODO: Throw HTTP error
            return -1;
        }
    }

}
