package com.na.quiz.activity;

import com.na.quiz.QuizApplication;
import com.na.quiz.R;
import com.na.quiz.domain.GamePlay;
import com.na.quiz.domain.Question;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity implements OnClickListener{

	private Question currentQ;
	private GamePlay currentGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        /**
         * Configure current game and get question
         */
        currentGame = ((QuizApplication)getApplication()).getCurrentGame();
        currentQ = currentGame.getNextQuestion();
		Button nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setOnClickListener(this);
        
        /**
         * Update the current question and answer options..
         */
        setQuestions();
		
    }


	/**
	 * Method to set the text for the question and answers from the current games
	 * current question
	 */
	private void setQuestions() {
		//set the question text from current question
        int i = currentGame.getRound();
		String question = i + ".) " + currentQ.getQuestion() + "?";
        TextView qText = (TextView) findViewById(R.id.question);
        qText.setText(question);
        
        //set the available options
        TextView option1 = (TextView) findViewById(R.id.answer1);
        option1.setText( currentQ.getOptionOne() );
        
        TextView option2 = (TextView) findViewById(R.id.answer2);
        option2.setText( currentQ.getOptionTwo() );
        
        TextView option3 = (TextView) findViewById(R.id.answer3);
        option3.setText( currentQ.getOptionThree() );
        
        TextView option4 = (TextView) findViewById(R.id.answer4);
        option4.setText( currentQ.getOptionFour() );
	}
    

	@Override public void onClick(View v) {
		//validate a checkbox has been selected
		if (!checkAnswer()) return;
		
		// check if end of game
		if (currentGame.isGameOver()){
			Intent i = new Intent(this, EndgameActivity.class);
			startActivity(i);
			finish();
		} else {
			Intent i = new Intent(this, QuestionActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	
	@Override public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK : return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	/**
	 * Check if a checkbox has been selected, and if it
	 * has then check if its correct and update gamescore
	 */
    public int rq=0;
	private boolean checkAnswer() {

        // RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        // final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();


        RadioGroup options = (RadioGroup) findViewById(R.id.group1);
        Integer selected = options.getCheckedRadioButtonId();
        String value = ((RadioButton) findViewById(options.getCheckedRadioButtonId())).getText().toString();
        //Toast.makeText(this, value,Toast.LENGTH_LONG).show();
        if (selected < 0) {
            // Toast.makeText(this, ( "false" ) ,Toast.LENGTH_LONG).show();
            return false;
        } else {
            //if (currentQ.getCorrectAnswer() == (selected+1) ) {
            //currentGame.incrementRightAnswers();
            // String ans = currentQ.getCorrectAnswer();
            // Toast.makeText(this,ans,Toast.LENGTH_LONG).show();
            // Toast.makeText(this, value,Toast.LENGTH_LONG).show();
            if (currentQ.getCorrectAnswer().equals(value)) {
                //Toast.makeText(this, value,Toast.LENGTH_LONG).show();
                currentGame.incrementRightAnswers();
            } else {
                // Toast.makeText(this, "elseeee" ,Toast.LENGTH_LONG).show();
                currentGame.incrementWrongAnswers();
            }
            // Toast.makeText(this, ( "True" ) ,Toast.LENGTH_LONG).show();
            return true;
        }
    }
    public void skipAnswer(View v) {
        currentGame.incrementSkipAnswers();
        if (currentGame.isGameOver()){
            Intent i = new Intent(this, EndgameActivity.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(this, QuestionActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void exit(View v)
    {
        //  moveTaskToBack(true);
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}