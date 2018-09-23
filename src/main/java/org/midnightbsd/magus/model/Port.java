package org.midnightbsd.magus.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Representation of data from Magus run_ports API. For example, http://www.midnightbsd.org/magus/api/run-ports-list?run=328&status=pass
 *
 * @author Lucas Holt
 */
@Entity
@Table(name = "ports")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Port implements Serializable {

    @Id
    @SequenceGenerator(name = "ports_id_seq",
            sequenceName = "ports_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ports_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "pkgname", length = 128)
    private String pkgName;

    @Column(name = "version", length = 32)
    private String version;

    @Column(name = "description", columnDefinition = "TEXT", length = 65616)
    private String description;

    /**
     * One or more licenses separated by spaces
     */
    @Column(name = "license", length = 64)
    private String license;

    @Column(name = "www", columnDefinition = "TEXT", length = 65616)
    private String www;

    @Column(name = "status", length = 32, nullable = false)
    private String status;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "updated", nullable = false)
    private Date updated;

    @Column(name = "blessed", nullable = false)
    private Boolean restricted;

    @ManyToOne
    @JoinColumn(name = "run")
    private Run run;

    @JsonIgnore
    @OneToMany(mappedBy = "port")
    private List<Event> events;

    @JsonIgnore
    @OneToOne(mappedBy = "log")
    private Log log;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "port_categories", joinColumns = @JoinColumn(name = "port", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category", referencedColumnName = "id"))
    private Set<Category> categories;
}