package com.example.demo.server.rpc;

import com.example.demo.server.RpcException;
import com.facebook.drift.annotations.ThriftMethod;
import com.facebook.drift.annotations.ThriftService;

@ThriftService
public interface IRpcService {

    @ThriftMethod
    RpcResponse invokeMethod(RpcRequest request) throws RpcException;
}
