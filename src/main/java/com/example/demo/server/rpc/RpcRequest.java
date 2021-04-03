package com.example.demo.server.rpc;

import com.facebook.drift.annotations.ThriftField;
import com.facebook.drift.annotations.ThriftStruct;
import com.google.common.collect.Lists;
import org.springframework.util.SerializationUtils;

import java.util.List;
import java.util.stream.Collectors;

@ThriftStruct
public class RpcRequest {

    public RpcRequest() {
    }

    public RpcRequest(String beanName, String methodName, List<Object> params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params.stream().map(o -> SerializationUtils.serialize(o)).collect(Collectors.toList());
    }

    @ThriftField(value = 1, requiredness = ThriftField.Requiredness.REQUIRED)
    public String beanName;
    @ThriftField(value = 2, requiredness = ThriftField.Requiredness.REQUIRED)
    public String methodName;
    @ThriftField(value = 3, requiredness = ThriftField.Requiredness.OPTIONAL)
    public List<byte[]> params;

}
