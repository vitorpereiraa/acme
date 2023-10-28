package com.isep.acme.schemas.sql;

import javax.persistence.Embeddable;

import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("sql")
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteSqlSchema {
    private String vote;
    private Long userID;
}
