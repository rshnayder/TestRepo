package com.example.domain.models;

import java.util.HashMap;
import java.util.Map;

import com.example.domain.enums.Gender;
import com.example.domain.enums.UserRole;
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
        Map<String, String> map = new HashMap<>();
        map.put("age", age.toString());
        map.put("gender", gender);
        map.put("login", login);
        map.put("password", password);
        map.put("role", role);
        map.put("screenName", screenName);

        if (id != null) {
            map.put("id", id.toString());
        }
        return map;
    }
}
