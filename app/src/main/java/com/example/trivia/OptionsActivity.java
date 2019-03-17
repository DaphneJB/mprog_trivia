package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {
    private String selectedLevel;
    private String questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void buttonClicked(View view) {
        RadioGroup buttons = findViewById(R.id.difficulty);
        int selected = buttons.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selected);
        selectedLevel = selectedButton.getText().toString();
        //show the user which difficulty level is selected
        Toast.makeText(getApplicationContext(), selectedLevel +" is selected", Toast.LENGTH_SHORT).show();
    }

    public void startGameClicked(View view) {
        EditText numberOfQuestions = findViewById(R.id.numberQuestion);
        //get the number of questions
        questions = numberOfQuestions.getText().toString();
        System.out.println("Number of questions " + questions);
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("questionAmount", questions);
        intent.putExtra("level", selectedLevel);
        startActivity(intent);

    }
}
