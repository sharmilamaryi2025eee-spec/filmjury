package com.filmjury.util;

public class ScoreUtil {

    private ScoreUtil() {
    }

    public static double calculateTotal(
            int story,
            int direction,
            int acting,
            int cinematography,
            int editing,
            int sound,
            int creativity,
            int overall) {

        return story
                + direction
                + acting
                + cinematography
                + editing
                + sound
                + creativity
                + overall;
    }
}