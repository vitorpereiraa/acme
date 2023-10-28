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
@Table(name = "Product")
public class ProductSqlSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;
}
