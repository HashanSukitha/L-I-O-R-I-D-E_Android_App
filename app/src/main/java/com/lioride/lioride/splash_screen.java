package com.lioride.lioride;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.app.Activity;

public class splash_screen extends Activity
{
    private static int SPLASH_TIME_OUT = 3000;

    EditText Act_sts;
    DatabaseHelper mDatabaseHelper;
    String ActivationStatus="Not Activated";
    String isPWDproteted="0", PWD, Uname, EMail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

       mDatabaseHelper = new DatabaseHelper(this);

       new Handler().postDelayed(new Runnable() {@Override
            public void run() {
                checkActivation();
            }
        },SPLASH_TIME_OUT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    private void checkActivation()
    {


        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext())
        {
            ActivationStatus= data.getString(1).toString();
        }


        if (ActivationStatus.equals("Activated"))
        {

             Cursor userData = mDatabaseHelper.getUserSettings();
             while(userData.moveToNext())
            {
                isPWDproteted = userData.getString(0).toString();
                //PWD = userData.getString(2).toString();
                //Uname = userData.getString(3).toString();
                //EMail = userData.getString(4).toString();
            }
            if (isPWDproteted.equals("1"))
            {
                Intent loginIntent = new Intent(splash_screen.this,user_login.class);
                startActivity(loginIntent);
                finish();

            }
            else
            {
                Intent loginIntent = new Intent(splash_screen.this,Menu.class);
                startActivity(loginIntent);
                finish();
            }
        }
        else
        {
            Intent loginIntent = new Intent(splash_screen.this,Activation.class);
            startActivity(loginIntent);
            finish();
        }
    }



}
