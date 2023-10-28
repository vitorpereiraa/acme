package com.isep.acme.schemas.sql;

import javax.annotation.Resource;
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
public class ImageSqlSchema {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private ProductSqlSchema product;

    @Lob
    private Resource image;
}
