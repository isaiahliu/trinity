package org.trinity.cloud;

/**
 * Cloud service
 *
 * @author Isaiah Liu
 *
 */
public interface ICloudServer {
    public static enum Protocol {
        HTTP("http"),
        HTTPS("https"),
        TCP("tcp"),
        UDP("udp");
        private final String name;

        private Protocol(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    String getName();

    Protocol getProtocol();
}
