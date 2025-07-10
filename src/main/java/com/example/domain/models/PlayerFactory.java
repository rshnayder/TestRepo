package com.example.domain.models;

import java.util.Random;

import com.example.domain.enums.Gender;
import com.example.domain.enums.UserRole;
import com.example.utils.Randomizer;

import static com.example.domain.enums.Gender.*;
import static com.example.domain.enums.UserRole.*;

public class PlayerFactory {

    public static Player createDefaultPlayer() {
        return createCustomPlayer(25, MALE, USER);
    }

    public static Player createNotExistingPlayer() {
        return Player.builder()
                .age(33)
                .gender(MALE.toString())
                .login(SUPERVISOR.getRole())
                .id(9999999999L)
                .password(Randomizer.randomPassword(16))
                .role(SUPERVISOR.getRole())
                .screenName("screen_" +  System.currentTimeMillis())
                .build();
    }



    public static Player createCustomPlayer(int age, Gender gender, UserRole role) {
        return Player.builder()
                .age(age)
                .gender(gender.toString())
                .login(role + "_" + System.currentTimeMillis())
                .password(Randomizer.randomPassword(16))
                .role(role.getRole())
                .screenName("screen_" + role + "_" + System.currentTimeMillis())
                .build();
    }

    public static Player createPlayerWithAgeRestriction() {
        return createCustomPlayer(15, MALE, USER);
    }

    //Player.getGender() type can be refactored to String
    public static Player createPlayerWithInvalidGender() {
        return createCustomPlayer(60, INVALID_GENDER, USER);
    }


    public static Player createAdminPlayer() {
        return Player.builder()
                .age(30)
                .gender(FEMALE.toString())
                .login("admin_" + System.currentTimeMillis())
                .password("adminPass123")
                .role(ADMIN.getRole())
                .screenName("AdminScreen_" + System.currentTimeMillis())
                .build();
    }


    public static Player createSupervisorPlayer() {
        return Player.builder()
                .age(40)
                .gender(MALE.toString())
                .login("supervisor_" + System.currentTimeMillis())
                .password("superPass123")
                .role(SUPERVISOR.getRole())
                .screenName("SupervisorScreen_" + System.currentTimeMillis())
                .build();
    }
}
