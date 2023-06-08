package com.example.springsecurityy.Controller;

import com.example.springsecurityy.Model.Order;
import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Service.OrderService;
import com.example.springsecurityy.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getOrders(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(orderService.getOrders(myUser.getId()));
    }
    @GetMapping("/get-all")
    public ResponseEntity getOrders(){
        return ResponseEntity.status(200).body(orderService.getALLOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser, @RequestBody OrderDTO order){
        orderService.addOrder(myUser.getId(), order);
        return ResponseEntity.status(200).body("Order Added");
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody OrderDTO order, @PathVariable Integer orderId){
        orderService.updateOrder(myUser.getId(), order,orderId);
        return ResponseEntity.status(200).body("order Updated");
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId){
        orderService.deleteOrder(myUser.getId(),orderId);
        return ResponseEntity.status(200).body("order Deleted");
    }

    @GetMapping("/get-id/{orderId}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId){

        return ResponseEntity.status(200).body(orderService.getOrderById(myUser.getId(),orderId));
    }

    @PutMapping("/status/{status}/{orderId}")
    public ResponseEntity getOrders(@PathVariable String status , @PathVariable Integer orderId){
        orderService.changeStatus(status,orderId);
        return ResponseEntity.status(200).body("status changed!");
    }



}
