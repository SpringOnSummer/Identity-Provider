package org.project.personal.identity_provider.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 CREATE TABLE users(
 user_id BIGINT AUTO_INCREMENT,
 user_name VARCHAR(100) NOT NULL ,
 email_local VARCHAR(100) NOT NULL ,
 email_domain VARCHAR(100) NOT NULL ,
 password VARCHAR(100) NOT NULL ,
 CONSTRAINT
 PRIMARY KEY (user_id)
 );
 */

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_local")
    private String emailLocal;

    @Column(name = "email_domain")
    private String emailDomain;

    @Column(name = "password")
    private String password;

    @Builder
    public User(Long id, String userName, String emailLocal, String emailDomain, String password) {
        this.id = id;
        this.userName = userName;
        this.emailLocal = emailLocal;
        this.emailDomain = emailDomain;
        this.password = password;
    }
}


