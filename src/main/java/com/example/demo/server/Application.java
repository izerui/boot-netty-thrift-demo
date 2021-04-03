package com.example.demo.server;

import com.example.demo.server.service.ExampleService;
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

@EnableConfigurationProperties(DriftProperties.class)
@SpringBootApplication
public class Application {

    @Autowired
    private ExampleService exampleService;

    @Bean
    public DriftServer driftServer(DriftProperties config) {
        DriftServer driftServer = new DriftServer(
                new DriftNettyServerTransportFactory(config),
                new ThriftCodecManager(),
                new NullMethodInvocationStatsFactory(),
                ImmutableSet.of(
                        new DriftService(exampleService, Optional.empty(), true)
                ),
                ImmutableSet.of());
        return driftServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

