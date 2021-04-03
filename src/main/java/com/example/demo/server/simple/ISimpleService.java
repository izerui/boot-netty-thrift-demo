package com.example.demo.server.simple;

import com.example.demo.server.RpcException;
import com.facebook.drift.annotations.ThriftMethod;
import com.facebook.drift.annotations.ThriftService;

@ThriftService
public interface ISimpleService {

    @ThriftMethod
    String hello(UserName userName) throws RpcException;
}
