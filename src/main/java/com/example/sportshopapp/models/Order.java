package com.example.sportshopapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String email;
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;
    private String province;
    private String district;
    private String ward;

    @Column(name = "shipped_date", nullable = true)
    private LocalDate shippedDate;

    @Column(name = "delivery_money", nullable = true)
    private double deliveryMoney;

    @Column(name = "total_amount")
    private double totalAmount;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod = PaymentMethod.CASH_ON_DELIVERY;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;
}
