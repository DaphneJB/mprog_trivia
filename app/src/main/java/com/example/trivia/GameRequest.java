package com.example.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<Question> questions;
    private ArrayList<String> incorrectAnswer;
    private JSONArray category;
    private Callback activity;
    private String amount, difficulty;

    public GameRequest(Context cont, String amount, String difficulty) {
        this.amount = "amount=" + amount;
        this.difficulty = "&difficulty=" + difficulty;
        if (difficulty.equals("random")) this.difficulty = "";
        context = cont;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestionsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            questions = new ArrayList<>();
            category = response.getJSONArray("results");
            for(int i = 0; i < category.length(); i++) {
                incorrectAnswer = new ArrayList<>();
                JSONObject item = category.getJSONObject(i);
                JSONArray incorrect = item.getJSONArray("incorrect_answers");
                //get incorrect answer options
                for (int j = 0; j < incorrect.length(); j++) {
                    incorrectAnswer.add(incorrect.getString(j));
                }
                questions.add(new Question(item.getString("question"), item.getString("difficulty"),
                        item.getString("correct_answer"), incorrectAnswer));
            }
            activity.gotQuestions(questions);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public interface Callback {
        void gotQuestions(ArrayList<Question>questions);
        void gotQuestionsError(String message);
    }

    public void getQuestions(Callback activity) {
        this.activity =activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        //get questions with a predefined amount and difficulty level
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://opentdb.com/api.php?" + amount + difficulty, null, this, this);
        queue.add(jsonObjectRequest);
    }
}
