package com.example.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Score> {
    private ArrayList<Score> score;

    public ScoreAdapter(Context context, int resource, ArrayList<Score> objects) {
        super(context, resource, objects);
        score = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
        }
        TextView name = convertView.findViewById(R.id.score);
        name.setText(score.get(position).getScore());

        TextView price = convertView.findViewById(R.id.name);
        price.setText(score.get(position).getName());

        return convertView;

    }
}
