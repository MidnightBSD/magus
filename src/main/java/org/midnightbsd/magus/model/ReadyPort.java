package org.midnightbsd.magus.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "ready_ports")
public class ReadyPort {

    @Column(name = "id")
    private int id;

    @Column(name = "run")
    private int run;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "pkgname", length = 128)
    private String pkgName;

    @Column(name = "version", length = 32)
    private String version;

    @Column(name = "description", columnDefinition = "TEXT", length = 65616)
    private String description;

    @Column(name = "license", length = 64)
    private String license;

    @Column(name = "www", columnDefinition = "TEXT", length = 65616)
    private String www;

    @Column(name = "status", length = 32, nullable = false)
    private String status;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "updated", nullable = false)
    private Date updated;

    @Column(name = "priority", nullable = false)
    private Long priority;

}