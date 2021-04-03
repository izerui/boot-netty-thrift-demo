package com.example.demo.client;

import com.example.demo.server.service.RpcException;
import com.example.demo.server.service.UserName;
import com.facebook.drift.annotations.ThriftMethod;
import com.facebook.drift.annotations.ThriftService;

@ThriftService
public interface TestExampleService {

    @ThriftMethod
    String hello(UserName userName) throws RpcException;
}
