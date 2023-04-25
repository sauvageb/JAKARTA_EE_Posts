package com.example.demo.model;

import com.example.demo.api.dto.UserDto;

public class User {

    private Long id;

    private String username;

    private String password;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDto toDto(){
        UserDto dto = new UserDto();
        dto.setId(this.getId());
        dto.setUsername(this.getUsername());
        return dto;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
