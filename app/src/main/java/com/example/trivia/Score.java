package com.example.trivia;

public class Score {
    private String score, name;

    public Score(String score, String name) {
        this.score = score;
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
