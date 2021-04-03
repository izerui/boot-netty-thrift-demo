package com.example.demo.server.service;

import com.facebook.drift.annotations.ThriftConstructor;
import com.facebook.drift.annotations.ThriftField;
import com.facebook.drift.annotations.ThriftStruct;

@ThriftStruct
public class RpcException extends RuntimeException{

    private int code;

    @ThriftConstructor
    public RpcException(int code, String message) {
        super(message);
        this.code = code;
    }

    @ThriftField(1)
    public int getCode() {
        return code;
    }


    @ThriftField(2)
    public String getMessage(){
        return super.getMessage();
    }
}
