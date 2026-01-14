package com.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usergroups")
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userGroup", nullable = false)
    private String userGroup;

    @Column(name = "username", nullable = false)
    private String username;

    // ❌ AM ȘTERS: Lista de Users și @ManyToMany.
    // Nu avem nevoie de ea și cauza eroarea 500.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}