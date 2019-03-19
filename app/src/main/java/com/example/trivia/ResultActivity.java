package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        view.setEnabled(false);
    }

    @Override
    public void gotScores(ArrayList<Score> scores) {
        System.out.println("scores " + scores.size());
        System.out.println("wat is dit " + scores.get(0).getName());
        Collections.sort(scores, new SortByScore());
        ListAdapter adapter = new ScoreAdapter(this, R.layout.activity_result, scores);
        ListView view = findViewById(R.id.view);
        view.setAdapter(adapter);
    }

    @Override
    public void gotScoresError(String message) {

    }
}
