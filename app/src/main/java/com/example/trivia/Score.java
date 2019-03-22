package com.example.trivia;

//represents a score class with a score and a given name
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
