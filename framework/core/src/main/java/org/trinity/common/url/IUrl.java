package org.trinity.common.url;

/**
 * @author Isaiah Liu
 */
public interface IUrl {
    default String getFullPath() {
        String path = getPath();

        if (path != null && path.length() > 0) {
            path = getSplitter() + path;
        }

        final IUrl parent = getParent();

        if (parent != null) {
            path = getParent().getFullPath() + path;
        }

        return path;
    }

    IUrl getParent();

    String getPath();

    default String getSplitter() {
        return "/";
    }
}
