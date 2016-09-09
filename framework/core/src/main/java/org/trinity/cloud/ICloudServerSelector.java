package org.trinity.cloud;

/**
 * Get url for a service
 *
 * @author Isaiah Liu
 */
public interface ICloudServerSelector {
    String getUrl(ICloudServer server);
}
