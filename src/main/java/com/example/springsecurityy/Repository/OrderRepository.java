package com.example.springsecurityy.Repository;

import com.example.springsecurityy.Model.Order;
import com.example.springsecurityy.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findOrdersByUser(MyUser myUser);
    Order findOrdersById(Integer id);
}
