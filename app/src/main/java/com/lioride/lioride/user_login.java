package com.lioride.lioride;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_login extends AppCompatActivity
{
    DatabaseHelper mDatabaseHelper;
    Button logInButton,exitButton;
    String PWD="",Uname="";
    EditText usernameTxt,pwdTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mDatabaseHelper = new DatabaseHelper(this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logInButton =  (Button) findViewById(R.id.logInButton);
        exitButton  =  (Button) findViewById(R.id.exitButton);





        logInButton.setOnClickListener(new View.OnClickListener()
        {@Override
        public void onClick(View v)
        {

            validateUser();
        }
        });

        exitButton.setOnClickListener(new View.OnClickListener()
        {@Override
            public void onClick(View v)
            {
                finish();
            }
        });





    }

    private void validateUser()
    {
        usernameTxt =  (EditText) findViewById(R.id.usernameTxt);
        pwdTxt      =  (EditText) findViewById(R.id.pwdTxt);

        Cursor userData = mDatabaseHelper.getUserSettings();
        while(userData.moveToNext())
        {
            //isPWDproteted = userData.getString(0).toString();
            Uname = userData.getString(1).toString();
            PWD = userData.getString(2).toString();
            //EMail = userData.getString(4).toString();
        }

        if(Uname.equals(usernameTxt.getText().toString()))
        {
            if(PWD.equals(pwdTxt.getText().toString()))
            {
                Intent go = new Intent(user_login.this,Menu.class);
                startActivity(go);
                finish();
            }
            else
            {
                Toast.makeText(this,"Invalid Password", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Invalid User Name", Toast.LENGTH_SHORT).show();
        }

    }


}
