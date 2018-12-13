package com.example.demo.service;

import io.airlift.drift.annotations.ThriftField;
import io.airlift.drift.annotations.ThriftStruct;

@ThriftStruct
public class UserName {

    @ThriftField(value = 1,requiredness = ThriftField.Requiredness.REQUIRED)
    public String name;
}
