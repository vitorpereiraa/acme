package com.isep.acme.schemas.sql;

import javax.persistence.Embeddable;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("sql")
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteSqlSchema {
    @Field
    private String vote;
    @Field
    private Long userID;
}
