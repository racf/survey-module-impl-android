package com.cysout.sousystems.surveymodule.dto;

public class ResponseMessageDto {
    private String type;
    private String request;
    int code;
    String message;
    private Object content;

    public ResponseMessageDto() {
    }

    public ResponseMessageDto(String type, String request, int code, String message, Object content) {
        this.type = type;
        this.request = request;
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseMessageDto{" +
                "type='" + type + '\'' +
                ", request='" + request + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
