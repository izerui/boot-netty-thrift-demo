package com.example.demo.server.rpc;

import com.example.demo.server.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RpcService implements IRpcService {

    @Autowired
    private ApplicationContext applicationContext;

    public RpcResponse invokeMethod(RpcRequest request) throws RpcException {
        Object bean = applicationContext.getBean(request.beanName);
        Method method;
        Object result;
        if (request.params != null) {
            Object[] objects = request.params.stream().map(bytes -> SerializationUtils.deserialize(bytes)).toArray();
            List<? extends Class<?>> collect = request.params.stream().map(bytes -> SerializationUtils.deserialize(bytes).getClass()).collect(Collectors.toList());
            method = ReflectionUtils.findMethod(bean.getClass(), request.methodName, collect.toArray(new Class[collect.size()]));
            result = ReflectionUtils.invokeMethod(method, bean, objects);
        } else {
            method = ReflectionUtils.findMethod(bean.getClass(), request.methodName);
            result = ReflectionUtils.invokeMethod(method, bean);
        }
        RpcResponse response = new RpcResponse();
        response.beanName = request.beanName;
        response.methodName = request.methodName;
        if (result != null) {
            response.responseBody = SerializationUtils.serialize(result);
        }
        return response;
    }

}
