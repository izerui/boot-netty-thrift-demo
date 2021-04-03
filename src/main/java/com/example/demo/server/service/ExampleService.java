package com.example.demo.server.service;

import com.facebook.drift.annotations.ThriftMethod;
import com.facebook.drift.annotations.ThriftService;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@ThriftService
public class ExampleService {

    @ThriftMethod
    public String hello(UserName userName) throws RpcException {
        if (userName.name.equals("王者荣耀")) {
            return "你好: " + userName.name;
        } else {
            throw new RpcException(100,"你不是王者荣耀");
        }
    }


    @ThriftMethod
    public Map<String,Integer> getMap() throws RpcException {
        Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        return map;
    }
}
