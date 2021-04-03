package com.example.demo.server.rpc;

import com.facebook.drift.annotations.ThriftField;
import com.facebook.drift.annotations.ThriftStruct;
import org.springframework.util.SerializationUtils;

@ThriftStruct
public class RpcRequest {

    public RpcRequest() {
    }

    public RpcRequest(String beanName, String methodName, Object requestBody) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.requestBody = SerializationUtils.serialize(requestBody);
    }

    @ThriftField(value = 1, requiredness = ThriftField.Requiredness.REQUIRED)
    public String beanName;
    @ThriftField(value = 2, requiredness = ThriftField.Requiredness.REQUIRED)
    public String methodName;
    @ThriftField(value = 3, requiredness = ThriftField.Requiredness.OPTIONAL)
    public byte[] requestBody;

    public <R extends Object> R getRequestBodyObject(Class<R> rClass) {
        return (R) SerializationUtils.deserialize(requestBody);
    }
}
