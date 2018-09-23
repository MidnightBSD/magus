package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "logs")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Log {
    @Id
    @SequenceGenerator(name = "logs_id_seq",
            sequenceName = "logs_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "logs_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "port")
    private Port port;

    @Column(name = "data", columnDefinition = "TEXT", length = 65616)
    private String data;
}