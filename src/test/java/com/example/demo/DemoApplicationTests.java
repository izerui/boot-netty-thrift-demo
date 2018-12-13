package com.example.demo;

import com.example.demo.client.TestExampleService;
import com.example.demo.service.ExampleService;
import com.example.demo.service.UserName;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.net.HostAndPort;
import io.airlift.drift.client.DriftClient;
import io.airlift.drift.client.DriftClientFactory;
import io.airlift.drift.client.address.AddressSelector;
import io.airlift.drift.client.address.SimpleAddressSelector;
import io.airlift.drift.codec.ThriftCodecManager;
import io.airlift.drift.server.DriftServer;
import io.airlift.drift.server.DriftService;
import io.airlift.drift.server.stats.NullMethodInvocationStatsFactory;
import io.airlift.drift.transport.netty.client.DriftNettyClientConfig;
import io.airlift.drift.transport.netty.client.DriftNettyMethodInvokerFactory;
import io.airlift.drift.transport.netty.server.DriftNettyServerConfig;
import io.airlift.drift.transport.netty.server.DriftNettyServerTransportFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Value("${thrift.port}")
    private Integer port;

    @Autowired
    private ExampleService exampleService;

    private DriftServer driftServer;

    @Before
    public void beforeTest() {
        DriftNettyServerConfig config = new DriftNettyServerConfig()
                .setPort(port);
        this.driftServer = new DriftServer(
                new DriftNettyServerTransportFactory(config),
                new ThriftCodecManager(),
                new NullMethodInvocationStatsFactory(),
                ImmutableSet.of(new DriftService(exampleService, Optional.empty(), true)),
                ImmutableSet.of());
        driftServer.start();
    }

    @After
    public void afterTest() {
        this.driftServer.shutdown();
    }

    @Test
    public void test() {

        // server address
        List<HostAndPort> addresses = ImmutableList.of(HostAndPort.fromParts("localhost", port));

// expensive services that should only be created once
        ThriftCodecManager codecManager = new ThriftCodecManager();
        AddressSelector addressSelector = new SimpleAddressSelector(addresses, false);
        DriftNettyClientConfig config = new DriftNettyClientConfig();

// methodInvokerFactory must be closed
        DriftNettyMethodInvokerFactory<?> methodInvokerFactory = DriftNettyMethodInvokerFactory
                .createStaticDriftNettyMethodInvokerFactory(config);

// client factory
        DriftClientFactory clientFactory = new DriftClientFactory(codecManager, methodInvokerFactory, addressSelector);

        DriftClient<TestExampleService> client = clientFactory.createDriftClient(TestExampleService.class);


        UserName userName = new UserName();
        userName.name = "王者荣耀";

        String helloWorld = client.get().hello(userName);

        System.out.println("======================================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("--------------------------结果： "+helloWorld);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("======================================================");

    }

}

