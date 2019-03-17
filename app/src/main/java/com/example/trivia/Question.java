package com.example.trivia;

import java.util.ArrayList;

public class Question {
    private String question, difficulty, correctAnswer;
    private ArrayList incorrectAnswer;

    public Question(String question, String difficulty, String correctAnswer, ArrayList incorrectAnswer) {
        incorrectAnswer = new ArrayList<String>();
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }
}
