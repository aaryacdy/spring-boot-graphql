package com.arya.graphql.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Response {
    private Object object;
    private String message;
    @Builder.Default
    private boolean success = false;
}
