package com.example.demo.server.rpc;

import com.example.demo.server.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;

import java.lang.reflect.Method;

@Service
public class RpcService implements IRpcService {

    @Autowired
    private ApplicationContext applicationContext;

    public RpcResponse invokeMethod(RpcRequest request) throws RpcException {
        Object bean = applicationContext.getBean(request.beanName);
        Method method;
        Object result;
        if (request.requestBody != null) {
            Object param = SerializationUtils.deserialize(request.requestBody);
            method = ReflectionUtils.findMethod(bean.getClass(), request.methodName, param.getClass());
            result = ReflectionUtils.invokeMethod(method, bean, param);
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
