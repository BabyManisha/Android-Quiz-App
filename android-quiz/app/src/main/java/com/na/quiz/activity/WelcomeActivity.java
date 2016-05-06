package com.na.quiz.activity;

import java.util.List;

import com.na.quiz.QuizApplication;
import com.na.quiz.R;
import com.na.quiz.domain.GamePlay;
import com.na.quiz.domain.Question;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
/**
 * Menu page welcome screen 
 */
public class WelcomeActivity extends Activity {

    Button b1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       // b1 = (Button)findViewById(R.id.aboutBtn);

    }
    
    
    public void NewGame( View v ) throws ParseException{
        ConnectivityManager conMgr  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMgr.getActiveNetworkInfo();
        if(info != null && info.isConnected())
        {
            Intent i1 = new Intent(this,ChoiceActivity.class);
            startActivity(i1);
        }
        else
        {
            Toast.makeText(this,
                    "Please make sure you have internet connection",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void onAbout(View v)
    {
        Intent i1 = new Intent(this,AboutUsActivity.class);
        startActivity(i1);
    }
    public void exit(View v)
    {
     //   System.exit(0);
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
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
