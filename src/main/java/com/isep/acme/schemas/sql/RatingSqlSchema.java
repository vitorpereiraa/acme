package com.isep.acme.schemas.sql;

import javax.persistence.*;

import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("sql")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingSqlSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRating;

    @Version
    private long version;

    @Column(nullable = false)
    private Double rate;
}