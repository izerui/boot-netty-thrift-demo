package com.example.demo.client;

import com.example.demo.server.rpc.IRpcService;
import com.example.demo.server.rpc.RpcRequest;
import com.example.demo.server.rpc.RpcResponse;
import com.example.demo.server.simple.ISimpleService;
import com.example.demo.server.simple.UserName;
import com.facebook.drift.client.DriftClient;
import com.facebook.drift.client.DriftClientFactory;
import com.facebook.drift.client.address.AddressSelector;
import com.facebook.drift.client.address.SimpleAddressSelector;
import com.facebook.drift.codec.ThriftCodecManager;
import com.facebook.drift.transport.netty.client.DriftNettyClientConfig;
import com.facebook.drift.transport.netty.client.DriftNettyMethodInvokerFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.net.HostAndPort;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.util.SerializationUtils;

import java.util.List;

public class ClientTests {


    public DriftClientFactory driftClientFactory() {
        // server address
        List<HostAndPort> addresses = ImmutableList.of(HostAndPort.fromParts("localhost", 41001));

        // expensive services that should only be created once
        ThriftCodecManager codecManager = new ThriftCodecManager();
        AddressSelector addressSelector = new SimpleAddressSelector(addresses, true);
        DriftNettyClientConfig config = new DriftNettyClientConfig();

        // methodInvokerFactory must be closed
        DriftNettyMethodInvokerFactory<?> methodInvokerFactory = DriftNettyMethodInvokerFactory
                .createStaticDriftNettyMethodInvokerFactory(config);

        // client factory
        DriftClientFactory clientFactory = new DriftClientFactory(codecManager, methodInvokerFactory, addressSelector);
        return clientFactory;
    }

    @Test
    public void test() {

        DriftClientFactory clientFactory = driftClientFactory();

        DriftClient<ISimpleService> client = clientFactory.createDriftClient(ISimpleService.class);


        UserName userName = new UserName();
        userName.name = "王者荣耀";
        userName.data = SerializationUtils.serialize(Lists.newArrayList("a", "b", "c"));

        try {
            String helloWorld = client.get().hello(userName);

            System.out.println("======================================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("--------------------------结果： " + helloWorld);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("======================================================");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Test
    public void testException() {

        DriftClientFactory clientFactory = driftClientFactory();

        DriftClient<ISimpleService> client = clientFactory.createDriftClient(ISimpleService.class);

        UserName userName = new UserName();
        userName.name = "王者荣耀22";
        try {
            String helloWorld = client.get().hello(userName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @Test
    public void testRpc() {
        DriftClientFactory clientFactory = driftClientFactory();

        DriftClient<IRpcService> client = clientFactory.createDriftClient(IRpcService.class);

        UserName userName = new UserName();
        userName.name = "王者荣耀";

        RpcRequest request = new RpcRequest("simpleService", "hello", Lists.newArrayList(userName));
        RpcResponse response = client.get().invokeMethod(request);
        System.out.println(response.getResponseBodyObject());
    }

}

