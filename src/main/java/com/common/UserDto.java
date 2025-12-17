package com.common;

public class UserDto {
    private Long id;
    private String username;
    private String email;

    public UserDto(String email, Long id, String username) {
        this.email = email;
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
