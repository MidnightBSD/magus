package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "locks")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Lock {

    @Id
    @SequenceGenerator(name = "locks_id_seq",
            sequenceName = "locks_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "locks_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    // TODO: should we map these
    @Column(name = "port", nullable = false)
    private int port;

    @Column(name = "machine", nullable = false)
    private int machine;
}