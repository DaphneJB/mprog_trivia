package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements GameRequest.Callback{
    private ArrayList questions;
    private int questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionNumber = 0;
        Intent intent = getIntent();
        //get amount of questions and level of difficulty
        String amount = (String) intent.getSerializableExtra("questionAmount");
        String level = (String) intent.getSerializableExtra("level");
        GameRequest req = new GameRequest(this, amount, level);
        req.getQuestions(this);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        updateScreen();
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void updateScreen() {
        Question question =  (Question) questions.get(questionNumber);
        TextView q = findViewById(R.id.quest);
        q.setText(question.getQuestion());
        ArrayList answers = question.getAllAnswers();
        for(int i = 0; i < answers.size(); i++) {
            Button myButton = new Button(this);
            myButton.setText(answers.get(i).toString());

            LinearLayout ll = findViewById(R.id.buttonLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
        questionNumber++;
    }

    public void answerClicked(View view) {
        System.out.println("aantal " + questions.size());
        System.out.println("aantal1 " + questionNumber);
        if(questionNumber < questions.size()) {
            ViewGroup layout = (ViewGroup) findViewById(R.id.buttonLayout);
            layout.removeAllViews();
            updateScreen();
        }
        else {

        }
    }
}
