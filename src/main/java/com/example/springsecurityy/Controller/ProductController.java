package com.example.springsecurityy.Controller;

import com.example.springsecurityy.Model.MyUser;
import com.example.springsecurityy.Model.Product;

import com.example.springsecurityy.Service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product){
        productService.addProduct( product);
        return ResponseEntity.status(200).body("Product Added");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product, @PathVariable Integer productId){
        productService.updateProduct(product,productId);
        return ResponseEntity.status(200).body("product Updated");
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(200).body("product Deleted");
    }

    @GetMapping("/get-id/{productId}")
    public ResponseEntity getproductById(@PathVariable Integer productId){

        return ResponseEntity.status(200).body(productService.getProductById(productId));
    }

}
