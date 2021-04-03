package com.example.demo.server;

import com.example.demo.server.rpc.RpcService;
import com.example.demo.server.simple.SimpleService;
import com.facebook.drift.codec.ThriftCodecManager;
import com.facebook.drift.server.DriftServer;
import com.facebook.drift.server.DriftService;
import com.facebook.drift.server.stats.NullMethodInvocationStatsFactory;
import com.facebook.drift.transport.netty.server.DriftNettyServerTransportFactory;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@EnableConfigurationProperties(RpcProperties.class)
@SpringBootApplication
public class Application {

    @Autowired
    private SimpleService exampleService;
    @Autowired
    private RpcService rpcService;

    @Bean
    public DriftServer driftServer(RpcProperties config) {
        DriftServer driftServer = new DriftServer(
                new DriftNettyServerTransportFactory(config),
                new ThriftCodecManager(),
                new NullMethodInvocationStatsFactory(),
                ImmutableSet.of(
                        new DriftService(exampleService, Optional.empty(), true),
                        new DriftService(rpcService, Optional.empty(), true)
                ),
                ImmutableSet.of());
        System.out.println("端口开始监听于: " + config.getPort());
        return driftServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

