package com.example.demo.client;

import com.example.demo.service.UserName;
import io.airlift.drift.annotations.ThriftMethod;
import io.airlift.drift.annotations.ThriftService;

@ThriftService
public interface TestExampleService {

    @ThriftMethod
    String hello(UserName userName);
}
