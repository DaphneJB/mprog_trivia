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

//this request class is used to get a all the scores on the board as well as posting new ones on it
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
        scores = new ArrayList<Score>();
        try {
            //get scores on the board
            JSONArray jsonArray = new JSONArray(response);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                scores.add(new Score(jsonObject.getString("score"), jsonObject.getString("name")));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        //check if there are scores on the board
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
        //post score
        PostRequest request = new PostRequest(Request.Method.POST, url, this, this, name, score);
        //get scores on the board
        String scores = "https://ide50-daphnejb.legacy.cs50.io:8080/list";
        Request requestScores = new StringRequest(Request.Method.GET, scores, this, this);
        queue.add(request);
        queue.add(requestScores);
    }
}
