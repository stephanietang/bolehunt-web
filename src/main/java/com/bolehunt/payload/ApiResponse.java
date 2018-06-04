package com.bolehunt.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    private int code = SUCCESS;
    private String message = "SUCCESS";
    private T data;

    public ApiResponse() {
        super();
    }

    public ApiResponse(String message) {
        super();
        this.message = message;
    }

    public ApiResponse(T data) {
        super();
        this.data = data;
    }

    public ApiResponse(String message, T data) {
        super();
        this.data = data;
        this.message = message;
    }

    public ApiResponse(Throwable e) {
        super();
        this.message = e.toString();
        this.code = FAIL;
    }
}
