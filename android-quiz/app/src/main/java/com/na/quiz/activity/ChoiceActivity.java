package com.na.quiz.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.na.quiz.QuizApplication;
import com.na.quiz.R;
import com.na.quiz.domain.GamePlay;
import com.na.quiz.domain.Question;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.String;
import java.util.List;

public class ChoiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startNewGame( View v ) throws ParseException {
        ConnectivityManager conMgr  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMgr.getActiveNetworkInfo();
        if(info != null && info.isConnected())
        {
            ParseQuery<Question> query = ParseQuery.getQuery("Question");
            query.fromLocalDatastore();
            Button bt = (Button)v;
            String bst = bt.getText().toString();
            //Toast.makeText(this,bst,Toast.LENGTH_SHORT).show();
            query.whereEqualTo("Type", bst);
            List<Question> questions = query.find();

            if ( questions.size() > 0 ){
                startGameActivity( questions );
            } else {
                query = ParseQuery.getQuery("Question");
                final Activity ctx = this;
                query.findInBackground( new FindCallback<Question>() {
                    @Override public void done(List<Question> questions, ParseException e) {
                        if ( e == null ){
                            ParseObject.unpinAllInBackground("QUESTIONS");
                            ParseObject.pinAllInBackground( "QUESTIONS",  questions );
                            startGameActivity( questions );
                        } else {
                            Toast.makeText(ctx,
                                    "Error updating questions - please make sure you have internet connection",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
        else
        {
            Toast.makeText(this,
                    "Please make sure you have internet connection",
                    Toast.LENGTH_LONG).show();
        }

    }


    private void startGameActivity( List<Question> questions ){
        //Initialise Game with retrieved question set
        GamePlay c = new GamePlay();
        c.setQuestions(questions);
        c.setNumRounds( questions.size() );
        ((QuizApplication)getApplication()).setCurrentGame(c);

        //Start Game Now.. //
        Intent i = new Intent(this, QuestionActivity.class);
        startActivityForResult(i, 1);
    }

}

