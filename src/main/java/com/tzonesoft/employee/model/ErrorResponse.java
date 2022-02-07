package com.tzonesoft.employee.model;

public class ErrorResponse {
    private String message;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ErrorResponse(String message, Integer code) {
        this.message=message;
        this.code = code;
    }
}
