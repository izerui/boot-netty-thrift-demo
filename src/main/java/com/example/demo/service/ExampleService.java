package com.example.demo.service;

import io.airlift.drift.annotations.ThriftMethod;
import io.airlift.drift.annotations.ThriftService;
import org.springframework.stereotype.Service;

@Service
@ThriftService
public class ExampleService {

    @ThriftMethod
    public String hello(UserName userName) {
        return "你好: " + userName.name;
    }
}
