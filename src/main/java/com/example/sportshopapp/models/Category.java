package com.example.sportshopapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @JsonBackReference
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonManagedReference
    private List<Category> subCategories;

}
