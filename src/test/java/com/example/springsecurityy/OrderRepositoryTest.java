package com.example.springsecurityy;


import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Model.Product;
import com.example.springsecurityy.Repository.AuthRepository;
import com.example.springsecurityy.Repository.OrderRepository;
import com.example.springsecurityy.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AuthRepository myUserRepository;

    @Autowired
    ProductRepository productRepository;

    com.example.springsecurityy.Model.Order order, order1,order2,order3;
    Product product;
    MyUser user;

    List<Order> orderList;

    @BeforeEach
    void setUp(){

        user = new MyUser(null, "Nawaf", "123", "ADMIN", null);
        product = new Product(null, "Apple", 5.5,null);

        order1 = new com.example.springsecurityy.Model.Order(null,2,11.0,"NEW",new Date(),user,product);
        order1 = new com.example.springsecurityy.Model.Order(null,2,11.0,"NEW",new Date(),user,product);
        order1 = new com.example.springsecurityy.Model.Order(null,2,11.0,"NEW",new Date(),user,product);
    }


    @Test
    public void findOrdersByUser(){
        myUserRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        List<com.example.springsecurityy.Model.Order> orderList = null;
        orderList = orderRepository.findOrdersByUser(user);
        Assertions.assertThat(orderList.get(0).getUser().getId()).isEqualTo(user.getId());
    }
    }
