package com.lioride.lioride;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class userLog extends AppCompatActivity {

    private ListView logList;
    Button selectDateButton,backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logList = (ListView)findViewById(R.id.logList);
        populateListView();

        selectDateButton = (Button)findViewById(R.id.selectDateButton);
        backButton = (Button)findViewById(R.id.backButton);

        selectDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment dialogfragment = new DatePickerDialogTheme();
                dialogfragment.show(getFragmentManager(), "Theme");

            }
        });

        backButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent go = new Intent(userLog.this,Menu.class);
                startActivity(go);
                finish();
            }
        });
    }


    private void populateListView()
    {


       // Cursor data = mDatabaseHelper.getData();
        //ArrayList<String> listData = new ArrayList<>();
        //while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            //listData.add(data.getString(1));
        //}
        //create the list adapter and set the adapter
        //ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        //mListView.setAdapter(adapter);

        ArrayList<String> listData = new ArrayList<>();
        for(int i=0;i<=100;i++)
        {
            listData.add(Integer.toString(i));
            //logList.add();
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        logList.setAdapter(adapter);


    }
}


