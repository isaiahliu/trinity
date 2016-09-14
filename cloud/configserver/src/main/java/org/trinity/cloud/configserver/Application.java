package org.trinity.cloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Isaiah Liu
 *
 */
@SpringBootApplication
@EnableConfigServer
public class Application {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
