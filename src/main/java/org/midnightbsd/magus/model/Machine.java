package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "machines")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Machine {
    @Id
    @SequenceGenerator(name = "machines_id_seq",
            sequenceName = "machines_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "machines_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "arch", nullable = false, length = 12)
    private String arch;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "maintainer", nullable = false, length = 128)
    private String maintainer;

    @Column(name = "osversion", nullable = false, length = 16)
    private String osversion;

    @ManyToOne
    @JoinColumn(name = "run")
    private Run run;
}