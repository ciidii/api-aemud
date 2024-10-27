package com.amud.io.aemudapi.utils;

import org.springframework.http.HttpStatus;

public class ResponseVOBuilder<T> {
    private final ResponseVO<T> responseVO = new ResponseVO<>();

    private ResponseVOBuilder<T> result(String result) {
        responseVO.setResult(result);
        return this;
    }

    private ResponseVOBuilder<T> status(HttpStatus status) {
        responseVO.setStatus(status);
        return this;
    }

    public ResponseVOBuilder<T> success() {
        return new ResponseVOBuilder<T>().result("Succeed").status(HttpStatus.OK);
    }

    public ResponseVOBuilder<T> fail(HttpStatus status) {
        return new ResponseVOBuilder<T>().result("Failed").status(status);
    }

    public ResponseVOBuilder<T> error(ResponseErrorVo error,HttpStatus status) {
        responseVO.setError(error);
        responseVO.setResult("Failed");
        responseVO.setStatus(status);
        return this;
    }

    public ResponseVOBuilder<T> addData(final T body) {
        responseVO.setData(body);
        responseVO.setResult("Succeeded");
        responseVO.setStatus(HttpStatus.OK);
        return this;
    }

    public ResponseVO<T> build() {
        return responseVO;
    }
}