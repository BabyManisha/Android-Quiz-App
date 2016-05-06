package com.na.quiz.activity;

import java.util.List;

import com.na.quiz.QuizApplication;
import com.na.quiz.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.na.quiz.domain.GamePlay;
import com.na.quiz.domain.Question;
import com.parse.ParseException;
import com.parse.ParseQuery;

/**
 * Basic activity to display end results for the game
 *
 */
public class EndgameActivity extends Activity {
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_endgame);
		GamePlay currentGame = ((QuizApplication)getApplication()).getCurrentGame();
		// String result = "You Got " + currentGame.getRight() + "/" + currentGame.getNumRounds() + ".. ";
       // String result = "You Got " + currentGame.getRight() + "/" + currentGame.getNumRounds() + ".. ";
       // String result = "You Got " + currentGame.getRight() + "/ 5 .. ";
        String result = "Total Number of Questions are: 5\nNumber of Right Answers: "+currentGame.getRight()+"\nNumber of Wrong Answers: "+currentGame.getWrong()+"\nNumber of Skipped Questions: "+currentGame.getSkipped()+"\n";
		
		TextView results = (TextView)findViewById(R.id.endgameResult);
		results.setText( result );
	}
	
	
	@Override public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK )return true;
		return super.onKeyDown(keyCode, event);
	}


	public void startNewGame( View v ) throws ParseException {
            Intent i1 = new Intent(this,WelcomeActivity.class);
            startActivity(i1);
		/*
        ParseQuery<Question> query = ParseQuery.getQuery("Question");
		query.fromLocalDatastore();
		List<Question> questions = query.find();
		GamePlay c = new GamePlay();
		c.setQuestions(questions);
	    c.setNumRounds( questions.size() );
		((QuizApplication)getApplication()).setCurrentGame(c);  

		//Start new game..
		Intent i = new Intent(this, QuestionActivity.class);
		startActivityForResult(i, 1);
		*/
	}

    public void exit(View v)
{
    finish();
}
}