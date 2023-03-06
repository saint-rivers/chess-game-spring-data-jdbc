package com.example.demo.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/error")
public class ErrorController {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Error {
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String details;
    }

    @GetMapping("/player/limit")
    public Error getPlayerLimitError() {
        return Error.builder()
                .message("player limit exceeded")
                .build();
    }

    @GetMapping("/not-found")
    public Error getError() {
        return Error.builder()
                .message("the provided id is not valid")
                .build();
    }
}
