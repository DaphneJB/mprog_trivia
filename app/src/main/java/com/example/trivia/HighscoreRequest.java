package com.example.trivia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoreRequest implements Response.Listener<String>, Response.ErrorListener {
    private HighscoreRequest.Callback activity;
    private Context context;
    private String score, name;
    private ArrayList scores;

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
        System.out.println("wat " + response);
        scores = new ArrayList<Score>();
        try {
            JSONArray jsonoArray = new JSONArray(response);
            for(int i = 0; i < jsonoArray.length(); i++) {
                JSONObject jsonObject = jsonoArray.getJSONObject(i);
                System.out.println("wat is " + jsonObject);
                scores.add(new Score(jsonObject.getString("score"), jsonObject.getString("name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(scores.size() != 0) {
            activity.gotScores(scores);
        }

    }

    public interface Callback {
        void gotScores(ArrayList<Score> scores);
        void gotScoresError(String message);
    }

    public void postHighscore(HighscoreRequest.Callback activity) {
        this.activity = activity;
        String url = "https://ide50-daphnejb.legacy.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);
        PostRequest request = new PostRequest(Request.Method.POST, url, this, this, name, score);
        String scores = "https://ide50-daphnejb.legacy.cs50.io:8080/list";
        Request requestScores = new StringRequest(Request.Method.GET, scores, this, this);
        queue.add(request);
        queue.add(requestScores);
    }
}
