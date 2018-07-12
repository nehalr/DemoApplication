package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Neha Rathore on 7/10/2018.
 */

public class SecondActivity extends Activity {


    @Bind(R.id.name_data)
    TextView nametxt;
    @Bind(R.id.height_data)
    TextView heighttxt;
    @Bind(R.id.mass_data)
    TextView masstxt;
    @Bind(R.id.date_data)
    TextView datetxt;
    @Bind(R.id.back_img)
    ImageView backimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.second_screen);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("Id");
        String name = getIntent().getStringExtra("Name");
        String mass = getIntent().getStringExtra("mass");
        String height = getIntent().getStringExtra("height");
        String date = getIntent().getStringExtra("date");
        nametxt.setText(name);
        masstxt.setText(mass + " " + "kg");
        heighttxt.setText(height + " " + "meters");
        datetxt.setText(date);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

