package com.example.springsecurityy.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private Set<Order> orders;

}
