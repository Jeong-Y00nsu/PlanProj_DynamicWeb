package com.util;

import lombok.Data;

@Data
public class Response {

    Result result;
    String message;
    Object returnValue;
    
    public Response(){}

    public Response(Result result, String message){
        this.result = result;
        this.message = message;
    }
}
