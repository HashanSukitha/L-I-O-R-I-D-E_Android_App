package com.lioride.lioride;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activation extends AppCompatActivity {

    EditText activationCode,savedatabox;
    Button activationButton;
    Context c;
    RequestQueue requestQueue;
    String ActivationStatus="";

    DatabaseHelper mDatabaseHelper;
     Button btnAdd;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mDatabaseHelper = new DatabaseHelper(this);


       activationCode = (EditText) findViewById(R.id.activationCodetxt);
        activationButton = (Button) findViewById(R.id.ActivationBtn);


        //============================================================================================================================
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        activationButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                AddData("Activated");
                Intent homeIntent = new Intent(Activation.this, Menu.class);
                startActivity(homeIntent);
                finish();
 /*
                String showUrl = "http://www.lioride.com/Activation/getActivation.php?sc="+activationCode.getText();

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        showUrl, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {

                            JSONArray Activation = response.getJSONArray("Activation");
                            for(int i=0; i< Activation.length(); i++)
                            {
                                JSONObject Active  = Activation.getJSONObject(i);

                                String activationStatus = Active.getString("SCOOTER_STS");

                                ActivationStatus=activationStatus;

                                if(ActivationStatus.equals("1"))
                                {
                                    AddData("Activated");
                                    Intent homeIntent = new Intent(Activation.this, Menu.class);
                                    startActivity(homeIntent);
                                    finish();
                                }
                                else
                                {
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Activation.this);
                                    a_builder.setMessage("Do you want to Close this App !!!")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            })
                                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            }) ;
                                    AlertDialog alert = a_builder.create();
                                    alert.setTitle("Invalid Activation Code");
                                    alert.show();
                                }
                            }




                        } catch (JSONException e)
                        {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                });
                requestQueue.add(jsonObjectRequest);


*/
            }
        });
    }

    public void AddData(String newEntry)
    {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Rider Successfully Activated!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
