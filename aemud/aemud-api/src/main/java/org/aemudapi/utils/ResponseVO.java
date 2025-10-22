package org.aemudapi.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseVO<T> {
    private HttpStatus status;
    private String result;
    private ResponseErrorVo error;
    private T data;

    public ResponseEntity<ResponseVO<T>> toResponseEntity() {
        return new ResponseEntity<>(this, this.status);
    }

    public ResponseEntity<ResponseVO<T>> toResponseEntity(HttpStatus customStatus) {
        return new ResponseEntity<>(this, customStatus);
    }
}