package com.example.sportshopapp.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private Boolean success;
    private HttpStatus status;
    private String message;
    private T data;
}
