package com.amud.io.aemudapi.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseVO<T> {
    private HttpStatus status;
    private String result;
    private ResponseErrorVo error;
    private T data;
}