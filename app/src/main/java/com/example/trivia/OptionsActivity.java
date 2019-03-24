package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//Represents an settings menu where the user can select the difficulty level and the amount of questions
public class OptionsActivity extends AppCompatActivity {
    private String selectedLevel, questions;
    private int selectedDiff;
    private static final int NO_LEVEL_SELECTED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    //check which level of difficulty is selected by the user
    public void buttonClicked(View view) {
        RadioGroup buttons = findViewById(R.id.difficulty);
        selectedDiff = buttons.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selectedDiff);
        selectedLevel = selectedButton.getText().toString();
        //show the user which difficulty level is selected
        Toast.makeText(getApplicationContext(), selectedLevel +" is selected", Toast.LENGTH_SHORT).show();
    }

    //check the amount of questions selected
    public void startGameClicked(View view) {
        EditText amountOfQuestions = findViewById(R.id.numberQuestion);
        //get the amount of questions
        questions = amountOfQuestions.getText().toString();
        System.out.println("diff " + selectedDiff);
        //check if question amount is between 1 and 50. More or less questions are not allowed
        if (questions.equals("") || Integer.parseInt(questions) < 1 || Integer.parseInt(questions) > 50) {
            Toast.makeText(getApplicationContext(), "Please make sure to set the amount of questions" +
                    " between 1 and 50.", Toast.LENGTH_SHORT).show();
        }
        //check if a level of difficulty is selected
        else if(selectedDiff == NO_LEVEL_SELECTED) {
            Toast.makeText(getApplicationContext(), "Please make sure to choose a level of difficulty",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("questionAmount", questions);
            intent.putExtra("level", selectedLevel);
            startActivity(intent);
        }
    }
}
