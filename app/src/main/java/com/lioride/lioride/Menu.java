package com.lioride.lioride;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu extends Activity {

    Button renameButton, onOffButton, conDisConButton, userSettingsButton, logButton, exitButton;

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;
    Button showButton ;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDatabaseHelper = new DatabaseHelper(this);

        mListView = (ListView) findViewById(R.id.listView);
        showButton =(Button) findViewById(R.id.showBtn);

        showButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                populateListView();
            }
        });

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//========================================================================================================================================
        renameButton = (Button) findViewById(R.id.renameButton);

        renameButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(Menu.this,rename.class);
                startActivity(go);
                finish();
            }
        });
//========================================================================================================================================
        onOffButton = (Button) findViewById(R.id.logInButton);

        onOffButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(Menu.this,onOff.class);
                startActivity(go);
                finish();
            }
        });
//========================================================================================================================================
        conDisConButton = (Button) findViewById(R.id.conDicon);

        conDisConButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(Menu.this,conDisCon.class);
                startActivity(go);
                finish();
            }
        });
//========================================================================================================================================
        userSettingsButton = (Button) findViewById(R.id.userSettingsButton);

        userSettingsButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(Menu.this,userSettings.class);
                startActivity(go);
                finish();
            }
        });
//========================================================================================================================================
        logButton = (Button) findViewById(R.id.logButton);

        logButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(Menu.this,userLog.class);
                startActivity(go);
                finish();
            }
        });
//========================================================================================================================================
        exitButton = (Button) findViewById(R.id.exitButton);

        exitButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                //Intent go = new Intent(Menu.this,conDisCon.class);
                //startActivity(go);
                finish();
            }
        });
//========================================================================================================================================
    }

    private void populateListView()
    {


        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);


    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }



}
