package com.example.springsecurityy.Repository;

import com.example.springsecurityy.Model.Order;
import com.example.springsecurityy.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findProductById(Integer id);
}
