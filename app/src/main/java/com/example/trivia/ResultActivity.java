package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements HighscoreRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void highscoreClicked(View view) {
        Intent intent = getIntent();
        String score = "" + (int) intent.getSerializableExtra("score");
        EditText name = findViewById(R.id.name);
        HighscoreRequest req = new HighscoreRequest(this, score, name.getText().toString());
        req.postHighscore(this);
    }

    @Override
    public void gotScores(ArrayList<String> scores) {
        scores.get(0);
    }

    @Override
    public void gotScoresError(String message) {

    }
}
