package com.document.swagger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    private ErrorResponse errorResponse;
    private T body;

    public Response(T body){
        this.body = body;
    }

    public Response(ErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    }
}
