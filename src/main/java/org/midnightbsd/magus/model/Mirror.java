package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "mirrors")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Mirror {

    @Id
    @SequenceGenerator(name = "mirrors_id_seq",
            sequenceName = "mirrors_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "mirrors_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "country", length = 2)
    private String country;

    @Column(name = "url", length = 255)
    private String url;
}