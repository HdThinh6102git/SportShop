package com.example.sportshopapp.dtos;

import com.example.sportshopapp.models.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeDTO {
    private Long id;
    private String name;
    public static SizeDTO convertToSizeDTO(Size size){
        return SizeDTO.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
}
