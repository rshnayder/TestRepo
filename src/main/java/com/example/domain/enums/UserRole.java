package com.example.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    SUPERVISOR("supervisor"),
    ADMIN("admin"),
    USER("user");

    private final String role;
    
}
