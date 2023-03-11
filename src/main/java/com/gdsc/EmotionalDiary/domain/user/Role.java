package com.gdsc.EmotionalDiary.domain.user;

public enum Role {
    USER("USER"), ADMIN("ADMIN");

    private String roleName;
    Role(String roleName) {
        this.roleName = roleName;
    }
}
