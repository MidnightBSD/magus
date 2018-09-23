package org.midnightbsd.magus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Lucas Holt
 */
@Entity
@Table(name = "categories")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Category implements Serializable {

    @Id
    @SequenceGenerator(name = "categories_id_seq",
            sequenceName = "categories_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "categories_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "category", length = 64)
    private String category;
}
