package com.lioride.lioride;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class userSettings extends AppCompatActivity {

    Button backButton,saveButton;
    Switch protectionSwitch;
    EditText unameTxt,pwdTxt,recoveryEmailTxt;
    String isProtected=" ";
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        mDatabaseHelper = new DatabaseHelper(this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backButton = (Button) findViewById(R.id.backButton);
        saveButton =  (Button) findViewById(R.id.saveButton);
        protectionSwitch  = (Switch) findViewById(R.id.protectionSwitch);

        initializeSettings();



        backButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(userSettings.this,Menu.class);
                startActivity(go);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener()
        {@Override
            public void onClick(View v)
            {

                unameTxt= (EditText) findViewById(R.id.unameTxt);
                pwdTxt= (EditText) findViewById(R.id.pwdTxt);
                recoveryEmailTxt= (EditText) findViewById(R.id.recoveryEmailTxt);

                saveUserData(isProtected.toString(),unameTxt.getText().toString(),pwdTxt.getText().toString(),recoveryEmailTxt.getText().toString());

            }
        });

        protectionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //simpleSwitch.setChecked(true);

                Boolean switchState = protectionSwitch.isChecked();
                if(switchState==true)
                {
                    isProtected="1";
                }
                else
                {
                    isProtected="0";
                }
            }
        });



    }


    public void saveUserData(String isProtected,String unameTxt,String pwdTxt,String recoveryEmailTxt)
    {
        mDatabaseHelper.deleteUserSettings();
        boolean insertData = mDatabaseHelper.SaveUserSettings(isProtected,unameTxt,pwdTxt,recoveryEmailTxt);


        if (insertData)
        {
            Toast.makeText(this,"Settings Saved Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeSettings()
    {
        Cursor userData = mDatabaseHelper.getUserSettings();
        while(userData.moveToNext())
        {
            isProtected = userData.getString(0).toString();
            //Uname = userData.getString(1).toString();
            //PWD = userData.getString(2).toString();
            //EMail = userData.getString(4).toString();
        }

        if(isProtected.equals("1"))
        {
            protectionSwitch.setChecked(true);
        }
        else
        {
            protectionSwitch.setChecked(false);
        }
    }


}
