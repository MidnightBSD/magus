package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Run data from Magus. For example, see http://www.midnightbsd.org/magus/api/runs
 * <p>
 * {"status":"inactive","created":"2015-05-09 15:12:13.339435","arch":"amd64","osversion":"0.6","id":"298","blessed":0}
 *
 * @author Lucas Holt
 */
@Entity
@Table(name = "runs")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Run implements Serializable {

    @Id
    @SequenceGenerator(name = "runs_id_seq",
            sequenceName = "runs_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "runs_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @JsonProperty("osversion")
    @Column(name = "osversion", nullable = false, length = 16)
    private String osVersion;

    @Column(name = "arch", nullable = false, length = 11)
    private String arch;

    @Column(name = "status", nullable = false, length = 40)
    private String status;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "blessed", nullable = false)
    private Boolean blessed;

    @JsonIgnore
    @OneToMany(mappedBy = "run")
    private List<Port> ports;

    @JsonIgnore
    @OneToMany(mappedBy = "run")
    private List<Machine> machines;
}