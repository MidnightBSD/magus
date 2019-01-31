package org.midnightbsd.magus.model;

import java.io.Serializable;

/**
 * @author Lucas Holt
 */
public class DependId implements Serializable {
    int port;

    int dependency;

    String type;
}
