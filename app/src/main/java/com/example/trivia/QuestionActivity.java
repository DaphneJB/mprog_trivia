package com.example.trivia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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
    private Question question;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionNumber = 0;
        score = 0;
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

    //shows the appropriate question and answers
    public void updateScreen() {
        question =  (Question) questions.get(questionNumber);
        TextView q = findViewById(R.id.quest);
        q.setText(Html.fromHtml(question.getQuestion()));
        ArrayList answers = question.getAllAnswers();
        //make answer buttons
        for(int i = 0; i < answers.size(); i++) {
            Button myButton = new Button(this);
            myButton.setText(Html.fromHtml(answers.get(i).toString()));
            myButton.setOnClickListener(new ClickListener());
            LinearLayout ll = findViewById(R.id.buttonLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
        questionNumber++;
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button answer = (Button) v;
            //check if answer is correct
            checkAnswer(answer.getText().toString());
            //check if there are more questions left
            if(questionNumber < questions.size()) {
                ViewGroup layout = findViewById(R.id.buttonLayout);
                layout.removeAllViews();
                updateScreen();
            }
            //there are no more questions left
            else {
                goToResult();
            }
        }
    }

    //checks if the given answer is correct
    public void checkAnswer(String answer) {
        if(question.getCorrectAnswer().equals(answer)){
            //update score when correct answer
            score++;
            TextView totScore = findViewById(R.id.score);
            totScore.setText("Score: " + score);
            Toast.makeText(this, "correct", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "incorrect", Toast.LENGTH_LONG).show();
        }
    }

    public void goToResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        finish();
        System.out.println("help " + score);
        intent.putExtra("score", score);
        startActivity(intent);
    }

}
