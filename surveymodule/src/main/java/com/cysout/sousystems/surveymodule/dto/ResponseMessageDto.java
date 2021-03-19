package com.cysout.sousystems.surveymodule.dto;
/**
* Copyright 2021 CysOut Solutions and SouSystems
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**/
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
