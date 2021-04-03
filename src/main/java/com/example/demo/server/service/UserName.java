package com.example.demo.server.service;

import com.facebook.drift.annotations.ThriftField;
import com.facebook.drift.annotations.ThriftStruct;

@ThriftStruct
public class UserName {

    @ThriftField(value = 1,requiredness = ThriftField.Requiredness.REQUIRED)
    public String name;
}
