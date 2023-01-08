package com.example.collecter.domain.user.domain;

import com.example.collecter.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseUUIDEntity {

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(256)", nullable = true, unique = true)
    private String emile;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String userId;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;
}
