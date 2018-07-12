package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import com.example.adapter.PeopleAdapter;
import com.example.api.APIGetManager;
import com.example.api.ResponseInterface;
import com.example.model.People;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Neha Rathore on 7/10/2018.
 */

public class FirstScreenActivity extends Activity implements ResponseInterface {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    PeopleAdapter peopleAdapter;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.first_screen);
        builder = new AlertDialog.Builder(this);
        recyclerView = (RecyclerView) findViewById(R.id.people_recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar = (ProgressBar) findViewById(R.id.service_loader);
        linearLayoutManager = new LinearLayoutManager(FirstScreenActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (isNetworkAvailable(this)) {
            new APIGetManager(FirstScreenActivity.this, FirstScreenActivity.this).callAllPeople();
        } else {
            builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
            //Setting message manually and performing action on button click
            builder.setMessage("Server error")
                    .setCancelable(false)
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            new APIGetManager(FirstScreenActivity.this, FirstScreenActivity.this).callAllPeople();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();

                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Alert!");
            alert.show();
        }


    }


    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public Void parseResponse(String response) {
        ArrayList<People> peoplelist = new ArrayList<People>();
        try {
            JSONObject jsonobj = new JSONObject(response.toString());
            JSONArray jsonArray = jsonobj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                People people = new People();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                people.setName(name);
                String mass = jsonObject.getString("mass");
                people.setMass(mass);
                String height = jsonObject.getString("height");
                people.setHeight(height);
                String created = null;
                created = jsonObject.getString("created");
                people.setCreated(created);
                peoplelist.add(people);
            }


            peopleAdapter = new PeopleAdapter(this, peoplelist, recyclerView);
            recyclerView.setAdapter(peopleAdapter);


            peopleAdapter.setRecyclerClickListener(new PeopleAdapter.RecyclerClickListener() {


                @Override
                public void servicenumber(String serviceId, String Name, String mass, String height, String date) {
                    Intent i = new Intent(FirstScreenActivity.this, SecondActivity.class);
                    i.putExtra("Id", serviceId);
                    i.putExtra("Name", Name);
                    i.putExtra("mass", mass);
                    i.putExtra("height", height);
                    i.putExtra("date", date);
                    startActivity(i);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Void ErrorResponse(Exception e) {
        return null;
    }
}
