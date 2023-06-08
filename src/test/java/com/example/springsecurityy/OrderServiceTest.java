package com.example.springsecurityy;


import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Model.Order;
import com.example.springsecurityy.Model.Product;
import com.example.springsecurityy.Repository.OrderRepository;
import com.example.springsecurityy.Repository.ProductRepository;
import com.example.springsecurityy.Service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;

    Order order, order1,order2,order3;
    Product product;
    MyUser user;

    List<Order> orderList;

    @BeforeEach
    void setUp(){

        user = new MyUser(1, "Nawaf", "123", "ADMIN", null);
        product = new Product(null, "Orange", 10.0,null);

        order1 = new Order(null, 1, 10.0,"NEW", new Date(), user, product);
        order2 = new Order(null, 2, 20.0, "NEW",new Date(),  user, product);
        order3 = new Order(null, 3, 30.0,"INPROGRESS", new Date(), user, product);


        List<Order> orderList = null;
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);

    }

    @Test
    public void getOrders(){
        when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> orderList1 = orderService.getOrders();
        Assertions.assertEquals(orderList1,orderList);
        Assertions.assertEquals(3,orderList.size());

        verify(orderRepository, times(1)).findAll();
    }
    

    @Test
    public void deleteOrder(){
        when(orderRepository.findOrdersById(order1.getId())).thenReturn(order1);
        orderService.deleteOrder(user.getId(), order1.getId());
        verify(orderRepository,times(1)).findOrdersById(order1.getId());
        verify(orderRepository,times(1)).delete(order1);
    }

    @Test
    public void changeStatus(){
        when(orderRepository.findOrdersById(order1.getId())).thenReturn(order1);
        orderService.changeStatus("COMPLETE",order1.getId());
        verify(orderRepository,times(1)).findOrdersById(order1.getId());
        verify(orderRepository, times(1)).save(order1);
    }

}