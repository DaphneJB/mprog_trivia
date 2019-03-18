package com.example.trivia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;

public class HighscoreRequest implements Response.Listener<String>, Response.ErrorListener {
    private HighscoreRequest.Callback activity;
    private Context context;
    private String score, name;

    public HighscoreRequest(Context cont, String score, String name) {
        context = cont;
        this.score = score;
        this.name = name;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotScoresError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {

    }

    public interface Callback {
        void gotScores(ArrayList<String> scores);
        void gotScoresError(String message);
    }

    public void postHighscore(HighscoreRequest.Callback activity) {
        this.activity = activity;
        String url = "http://ide50-daphnejb.legacy.cs50.io:8080/list/post";
        RequestQueue queue = Volley.newRequestQueue(context);
        PostRequest request = new PostRequest(Request.Method.POST, url, this, this, name, score);
        queue.add(request);
    }
}
