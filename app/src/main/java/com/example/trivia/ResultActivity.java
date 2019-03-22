package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ResultActivity extends AppCompatActivity implements HighscoreRequest.Callback {
    private String score;
    private Button saveScore;
    private ListAdapter adapter;
    private ArrayList allScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        allScores = new ArrayList<Score>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        saveScore = findViewById(R.id.save);
        Intent intent = getIntent();
        score = "" + (int) intent.getSerializableExtra("score");
        TextView view = findViewById(R.id.totalScore);
        view.setText("Your score: " + score);

        if(savedInstanceState != null){
            System.out.println(savedInstanceState.getBoolean("buttonEnabled"));
            saveScore.setEnabled(savedInstanceState.getBoolean("buttonEnabled"));
            allScores = savedInstanceState.getParcelableArrayList("allScores");
            if(allScores.size() != 0) gotScores(allScores);
        }
    }

    public void highscoreClicked(View view) {
        EditText name = findViewById(R.id.name);
        HighscoreRequest req = new HighscoreRequest(this, score, name.getText().toString());
        req.postHighscore(this);
        saveScore = (Button) view;
        saveScore.setEnabled(false);
    }

    @Override
    public void gotScores(ArrayList<Score> scores) {
        allScores = scores;
        //sort scores from high to low
        Collections.sort(scores, new SortByScore());
        adapter = new ScoreAdapter(this, R.layout.activity_result, scores);
        ListView view = findViewById(R.id.view);
        view.setAdapter(adapter);
    }

    @Override
    public void gotScoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //save if the button is enabled and save highscore list
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("buttonEnabled", saveScore.isEnabled());
        outState.putParcelableArrayList("allScores", allScores);
    }
}
