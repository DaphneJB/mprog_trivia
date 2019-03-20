package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ResultActivity extends AppCompatActivity implements HighscoreRequest.Callback {
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        score = "" + (int) intent.getSerializableExtra("score");
        TextView view = findViewById(R.id.totalScore);
        view.setText("Your score: " + score);
    }

    public void highscoreClicked(View view) {
        EditText name = findViewById(R.id.name);
        HighscoreRequest req = new HighscoreRequest(this, score, name.getText().toString());
        req.postHighscore(this);
        view.setEnabled(false);
    }

    @Override
    public void gotScores(ArrayList<Score> scores) {
        Collections.sort(scores, new SortByScore());
        ListAdapter adapter = new ScoreAdapter(this, R.layout.activity_result, scores);
        ListView view = findViewById(R.id.view);
        view.setAdapter(adapter);
    }

    @Override
    public void gotScoresError(String message) {

    }
}
