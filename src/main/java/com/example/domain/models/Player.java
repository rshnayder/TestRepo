package com.example.domain.models;

import java.util.Map;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private Integer age;
    private String gender;
    private Long id;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public Map<String, String> toMap() {
            return Map.of(
                "age", age != null ? age.toString() : null,
                "gender", gender,
                "id", id != null ? id.toString() : null,
                "login", login,
                "password", password,
                "role", role,
                "screenName", screenName
            );
    }
}
