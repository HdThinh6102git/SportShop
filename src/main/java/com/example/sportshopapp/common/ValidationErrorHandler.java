package com.example.sportshopapp.common;

import com.example.sportshopapp.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
public class ValidationErrorHandler {
    public BaseResponse<Void> handleValidationErrors(BindingResult result) {
        List<String> errorMessages = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return BaseResponse.<Void>builder()
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .message(errorMessages.toString())
                .build();
    }
}
