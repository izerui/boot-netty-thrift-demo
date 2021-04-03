package com.example.demo.server.rpc;

import com.facebook.drift.annotations.ThriftField;
import com.facebook.drift.annotations.ThriftStruct;
import org.springframework.util.SerializationUtils;

@ThriftStruct
public class RpcResponse {

    public RpcResponse() {
    }

    public RpcResponse(String beanName, String methodName, Object responseBody) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.responseBody = SerializationUtils.serialize(responseBody);
    }

    @ThriftField(value = 1, requiredness = ThriftField.Requiredness.REQUIRED)
    public String beanName;
    @ThriftField(value = 2, requiredness = ThriftField.Requiredness.REQUIRED)
    public String methodName;
    @ThriftField(value = 3, requiredness = ThriftField.Requiredness.OPTIONAL)
    public byte[] responseBody;

    public <R extends Object> R getResponseBodyObject(Class<R> rClass) {
        return (R)SerializationUtils.deserialize(responseBody);
    }
}
