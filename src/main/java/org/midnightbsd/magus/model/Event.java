package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "events")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Event {

    @Id
    @SequenceGenerator(name = "events_id_seq",
            sequenceName = "events_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "events_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "phase", length = 16)
    private String phase;

    @Column(name = "type", length = 16)
    private String type;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "msg", columnDefinition = "TEXT", length = 65616)
    private String message;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "time", nullable = false)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "port")
    private Port port;

    @ManyToOne
    @JoinColumn(name = "machine")
    private Machine machine;
}