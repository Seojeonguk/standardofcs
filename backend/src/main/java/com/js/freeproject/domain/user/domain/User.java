package com.js.freeproject.domain.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String nickName;
    private String name;
    private String pass;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_USER'")
    private UserRole role;

    private String image;

    @Builder
    public User(String email, String nickName, String name, String pass, String image) {
        this.email = email;
        this.nickName = nickName;
        this.name = name;
        this.pass = pass;
        this.image = image;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /*
    public void updateUser(final User user, String imageUrl) {
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.name = user.getName();
        this.pass = user.getPass();
        this.image = imageUrl;
    }
    */
    
    public void updateUser(String imageUrl) {
    	this.image = imageUrl;
    }
}
