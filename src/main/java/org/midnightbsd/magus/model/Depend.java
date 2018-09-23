package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "depends")
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(DependId.class)
public class Depend implements Serializable {

    @Id
    @Column(name = "port", nullable = false)
    private int port;

    @Id
    @Column(name = "dependency", nullable = false)
    private int dependency;

    @Id
    @Column(name = "type", length = 10, nullable = false)
    private String type;
}
