package org.example.session3.hw9.Hw9_apiPayload.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDto {
    private final boolean isSuccess;
    private final String code;
    private final String message;
    private HttpStatus httpStatus;
}

