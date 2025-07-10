package com.example.utils;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer {


    public static long randomLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    public static long randomLong() {
        return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
    }

    public static String randomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789"
                + "!@#$%^&*()_+-=[]{}|;':,.<>?/";

        return RandomStringUtils.random(length, characters);
    }
}
