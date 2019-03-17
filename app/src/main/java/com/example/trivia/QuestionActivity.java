package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements GameRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        //get amount of questions and level of difficulty
        String amount = (String) intent.getSerializableExtra("questionAmount");
        String level = (String) intent.getSerializableExtra("level");
        GameRequest req = new GameRequest(this, amount, level);
        req.getQuestions(this);
    }

    @Override
    public void gotQuestions(ArrayList<Question> categories) {

    }

    @Override
    public void gotQuestionsError(String message) {

    }
}
