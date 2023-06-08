package com.example.springsecurityy.Service;

import com.example.springsecurityy.ApiException;
import com.example.springsecurityy.Model.Order;
import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Model.Product;
import com.example.springsecurityy.Repository.AuthRepository;
import com.example.springsecurityy.Repository.OrderRepository;
import com.example.springsecurityy.Repository.ProductRepository;
import com.example.springsecurityy.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;

    public List<Order> getOrders(Integer userId) {
        return orderRepository.findOrdersByUser(authRepository.findMyUsersById(userId));
    }
    public List<Order> getALLOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Integer userId, OrderDTO dto) {
        MyUser user = authRepository.findMyUsersById(userId);
        if( user == null)
            new ApiException("user is null!");
        Product product = productRepository.findProductById(dto.getProductId());
        if(product == null)
            new ApiException("product is null!");
        Double totalPrice = dto.getQuantity() * product.getPrice();
        Order order = new Order();
        Date date = new Date();
        order.setDate(date);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(totalPrice);
        order.setStatus("NEW");
        order.setProduct(product);
        order.setUser(user);
        orderRepository.save(order);
    }

    public void updateOrder(Integer userId, OrderDTO dto, Integer orderId) {
        Order oldOrder = orderRepository.findOrdersById(orderId);

        if (oldOrder == null ){
            throw new ApiException("Order Not found");
        }
        MyUser user = authRepository.findMyUsersById(userId);
        if (oldOrder.getUser() != user){
            throw new ApiException("Not Authorised");
        }
        if(!(oldOrder.getStatus().equalsIgnoreCase("new")))
            throw new ApiException("Cant delete order status has changed");
        oldOrder.setQuantity(dto.getQuantity());
        oldOrder.setTotalPrice(dto.getQuantity() * oldOrder.getProduct().getPrice());
        orderRepository.save(oldOrder);

    }

    public void deleteOrder(Integer userId, Integer blogId) {
        Order order = orderRepository.findOrdersById(blogId);

        if (order == null ){
                throw new ApiException("order Not found");
        }
        if(!(order.getStatus().equalsIgnoreCase("new")))
            throw new ApiException("Cant delete order status has changed");

        MyUser user = authRepository.findMyUsersById(userId);
        if (order.getUser() != user){
            throw new ApiException("Not Authorised");
        }
        orderRepository.delete(order);


    }

    public Order getOrderById(Integer userId , Integer orderId){
        Order order = orderRepository.findOrdersById(orderId);

        if (order == null ){
                        throw new ApiException("order Not found");
        }

        MyUser user = authRepository.findMyUsersById(userId);
        if (order.getUser() != user){
            throw new ApiException("Not Authorised");
        }
        return order;
    }

    public void changeStatus(String status , Integer orderId){
        Order order =orderRepository.findOrdersById(orderId);
        if(order == null)
            return;
        if(!(status.equalsIgnoreCase("INPROGRESS") || status.equalsIgnoreCase("COMPLETE")))
            return;
        order.setStatus(status);
        orderRepository.save(order);
    }


}
