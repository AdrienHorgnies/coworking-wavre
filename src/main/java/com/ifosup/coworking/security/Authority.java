package com.ifosup.coworking.security;

public enum Authority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ANONYMOUS("ROLE_ANONYMOUS");

    public String name;

    Authority(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
