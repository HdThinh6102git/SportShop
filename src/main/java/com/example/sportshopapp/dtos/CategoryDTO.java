package com.example.sportshopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotNull
    private String name;
    @JsonProperty("parent_id")
    private Long parentId;
}
