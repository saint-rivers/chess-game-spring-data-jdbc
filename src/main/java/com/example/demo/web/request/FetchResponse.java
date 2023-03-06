package com.example.demo.web.request;

import lombok.Builder;

@Builder
public record FetchResponse<T>(String message, T payload) {
}
