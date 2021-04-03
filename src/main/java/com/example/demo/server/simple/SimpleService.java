package com.example.demo.server.simple;

import com.example.demo.server.RpcException;
import org.springframework.stereotype.Service;

@Service
public class SimpleService implements ISimpleService {

    public String hello(UserName userName) throws RpcException {
        if (userName.name.equals("王者荣耀")) {
            return "你好: " + userName.name;
        } else {
            throw new RpcException(100, "你不是王者荣耀");
        }
    }

}
