package org.trinity.cloud.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Isaiah Liu
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
