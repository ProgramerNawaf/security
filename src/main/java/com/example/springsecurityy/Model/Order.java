package com.example.springsecurityy.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "my_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "quantity cant be null!")
    private Integer quantity;
    @NotNull(message = "total price cant be null!")
    private Double totalPrice;
    @Column(columnDefinition = "varchar(25) not null check (status='NEW' or status='INPROGRESS' or status='COMPLETE')")
    @NotNull(message = "role cant be null!")
    public String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private MyUser user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
}
