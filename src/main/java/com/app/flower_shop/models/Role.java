package com.app.flower_shop.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN,SELLER;

    @Override
    public String getAuthority() {
        return name();
    }
}
