package com.example.trivia;

import java.util.ArrayList;
import java.util.Collections;
//represents a question with a given level of difficulty, correct & incorrect answers
public class Question {
    private String question, difficulty, correctAnswer;
    private ArrayList allAnswers;

    public Question(String question, String difficulty, String correctAnswer, ArrayList incorrectAnswer) {
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        allAnswers = incorrectAnswer;
        allAnswers.add(correctAnswer);
        //shuffle all the possible answers
        Collections.shuffle(allAnswers);
    }

    public String getQuestion(){
        return question;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList getAllAnswers() {
        return allAnswers;
    }
}
