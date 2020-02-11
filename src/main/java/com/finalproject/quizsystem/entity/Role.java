package com.finalproject.quizsystem.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT("ROLE_STUDENT"), ADMIN("ROLE_ADMIN");

    Role(String role) {
        this.role = role;
    }

    String role;

    @Override
    public String getAuthority() {
        return role;
    }
}