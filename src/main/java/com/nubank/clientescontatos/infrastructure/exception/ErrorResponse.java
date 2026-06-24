package com.nubank.clientescontatos.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<ValidationErrorDetail> errors;

    @Getter
    @Builder
    public static class ValidationErrorDetail {
        private String field;
        private String message;
    }
}
