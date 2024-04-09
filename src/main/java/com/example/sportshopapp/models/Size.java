package com.example.sportshopapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sizes")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Size extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductSize> productSizes;
}
