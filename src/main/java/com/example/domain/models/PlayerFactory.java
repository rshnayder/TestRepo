package com.example.domain.models;

import java.util.Random;

import com.example.domain.enums.Gender;

import static com.example.domain.enums.Gender.MALE;

public class PlayerFactory {

    private static final Random RANDOM = new Random();


    public static Player createDefaultPlayer() {
        return Player.builder()
                .age(25)
                .gender(MALE.name()) // Стандартна стать
                .login("user_" + System.currentTimeMillis()) // Динамічний логін
                .password("password" + RANDOM.nextInt(9999)) // Генеруємо унікальний пароль
                .role("user") // Роль за замовчуванням
                .screenName("screen_" + System.currentTimeMillis()) // Динамічне ім'я екрана
                .build();
    }


    public static Player createCustomPlayer(int age, Gender gender, String role) {
        return Player.builder()
                .age(age)
                .gender(gender.toString())
                .login(role + "_" + System.currentTimeMillis())
                .password("password" + RANDOM.nextInt(9999))
                .role(role)
                .screenName("screen_" + role + "_" + System.currentTimeMillis())
                .build();
    }

    public static Player createAdminPlayer() {
        return Player.builder()
                .age(30)
                .gender("male")
                .login("admin_" + System.currentTimeMillis())
                .password("adminPass123")
                .role("admin")
                .screenName("AdminScreen_" + System.currentTimeMillis())
                .build();
    }


    public static Player createSupervisorPlayer() {
        return Player.builder()
                .age(40)
                .gender("female")
                .login("supervisor_" + System.currentTimeMillis())
                .password("superPass123")
                .role("supervisor")
                .screenName("SupervisorScreen_" + System.currentTimeMillis())
                .build();
    }
}
