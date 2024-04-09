package com.example.sportshopapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_sizes")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "size_id")
    @JsonBackReference
    private Size size;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
    private int quantity;
}
