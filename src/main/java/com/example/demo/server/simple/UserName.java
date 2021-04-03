package com.example.demo.server.simple;

import com.facebook.drift.annotations.ThriftField;
import com.facebook.drift.annotations.ThriftStruct;

import java.io.Serializable;

@ThriftStruct
public class UserName implements Serializable {

    @ThriftField(value = 1, requiredness = ThriftField.Requiredness.REQUIRED)
    public String name;

    @ThriftField(value = 2, requiredness = ThriftField.Requiredness.OPTIONAL)
    public byte[] data;
}
