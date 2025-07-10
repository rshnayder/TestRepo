package com.example.domain.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE,
    FEMALE,
    INVALID_GENDER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
