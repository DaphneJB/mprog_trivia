package com.example.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionAdapter extends ArrayAdapter<Question> {
    private ArrayList<Question> questionMenu;

    public QuestionAdapter(Context context, int resource, ArrayList<Question> objects) {
        super(context, resource, objects);
        questionMenu = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_item, parent, false);
        }
//        TextView name = convertView.findViewById(R.id.name);
//        name.setText(menu.get(position).getName());
//
//        TextView price = convertView.findViewById(R.id.price);
//        price.setText("€" + menu.get(position).getPrice());

        return convertView;

    }
}
