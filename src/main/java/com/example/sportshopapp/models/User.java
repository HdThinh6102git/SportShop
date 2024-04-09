package com.example.sportshopapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cart> carts;
}
