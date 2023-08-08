package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totalQuestionsTV,questionsTV;
    Button optionA,optionB,optionC,optionD,submitBt;
    int score=0,totalQuestions=QuestionsAnswers.questions.length,currQuestionNo=0;
    String selectedAns="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTV=findViewById(R.id.total_question);
        questionsTV=findViewById(R.id.questions);
        optionA=findViewById(R.id.ans_a);
        optionB=findViewById(R.id.ans_b);
        optionC=findViewById(R.id.ans_c);
        optionD=findViewById(R.id.ans_d);
        submitBt=findViewById(R.id.submit);
        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        submitBt.setOnClickListener(this);
        totalQuestionsTV.setText("Total Questions : "+ totalQuestions );
        generateQuestions();
    }
    @Override
    public void onClick(View v) {
        Button selectedBt=(Button) v;
        optionA.setBackgroundColor(Color.WHITE);
        optionB.setBackgroundColor(Color.WHITE);
        optionC.setBackgroundColor(Color.WHITE);
        optionD.setBackgroundColor(Color.WHITE);
        if (selectedBt.getId()==R.id.submit)
        {
            if (selectedAns.equals(QuestionsAnswers.correctAnswers[currQuestionNo]))
            {
                score++;
            }
            currQuestionNo++;
            generateQuestions();
        }
        else
        {
            selectedAns=selectedBt.getText().toString();
            selectedBt.setBackgroundColor(Color.GREEN);
        }
    }
    void generateQuestions()
    {
        if (currQuestionNo==totalQuestions)
        {
            endQuiz();
            return;
        }
        questionsTV.setText(QuestionsAnswers.questions[currQuestionNo]);
        optionA.setText(QuestionsAnswers.choices[currQuestionNo][0]);
        optionB.setText(QuestionsAnswers.choices[currQuestionNo][1]);
        optionC.setText(QuestionsAnswers.choices[currQuestionNo][2]);
        optionD.setText(QuestionsAnswers.choices[currQuestionNo][3]);
    }
    void endQuiz()
    {
        String status="";
        if (score>totalQuestions*0.60)
        {
            status="PASSED";
        }
        else {
            status="FAIL";
        }

        new AlertDialog.Builder(this)
                .setTitle(status)
                .setMessage("Your score is "+ score + " out of " + totalQuestions)
                .setPositiveButton("Restart",(dialogInterface,i)->restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz()
    {
        score=0;
        currQuestionNo=0;
        generateQuestions();

    }

}