package com.amud.io.aemudapi.utils;

import org.springframework.http.HttpStatus;

public class ResponseVO<T> {
    private HttpStatus status;
    private String result;
    private ResponseErrorVo error;
    private T data;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
    public ResponseErrorVo getError() {
        return error;
    }
    public void setError(ResponseErrorVo error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}