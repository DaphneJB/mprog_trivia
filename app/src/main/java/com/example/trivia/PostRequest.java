package com.example.trivia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends StringRequest {
    private String name, score;

    public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String name, String score) {
        super(method, url, listener, errorListener);
        this.name = name;
        this.score = score;
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("score", score);
        return params;
    }
}
