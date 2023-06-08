package com.example.springsecurityy.Service;

import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Model.Order;
import com.example.springsecurityy.Model.Product;
import com.example.springsecurityy.Repository.AuthRepository;
import com.example.springsecurityy.Repository.OrderRepository;
import com.example.springsecurityy.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product, Integer productId) {
        Product oldProduct = productRepository.findProductById(productId);

        if (oldProduct == null ){
//            throw new ApiException("Blog Not found");
            return;
        }

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }

    public void deleteProduct( Integer productId) {
        Product oldProduct = productRepository.findProductById(productId);

        if (oldProduct == null ){
//            throw new ApiException("Blog Not found");
            return;
        }

        productRepository.delete(oldProduct);


    }

    public Product getProductById(Integer productId){
        Product product = productRepository.findProductById(productId);

        if (product == null ){
            //            throw new ApiException("Blog Not found");
            return null;
        }

        return product;
    }
}
