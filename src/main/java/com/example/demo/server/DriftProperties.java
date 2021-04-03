package com.example.demo.server;

import com.facebook.drift.transport.netty.server.DriftNettyServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "drift")
public class DriftProperties extends DriftNettyServerConfig {
}
