package com.group4.gici4.itc.quickhelps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ChoosePic extends AppCompatActivity implements View.OnClickListener{

    private ImageView ambulance, firefighter, police, taxi, image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);

        ambulance = (ImageView) findViewById(R.id.ambulance_pic);
        ambulance.setOnClickListener(this);
        firefighter = (ImageView) findViewById(R.id.firefighter_pic);
        firefighter.setOnClickListener(this);
        police = (ImageView) findViewById(R.id.police_pic);
        police.setOnClickListener(this);
        taxi = (ImageView) findViewById(R.id.taxi_pic);
        taxi.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image_pic);
        image.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent data = new Intent();
        if (v.getId() == R.id.ambulance_pic){
            data.putExtra("imagename", "ambulance");
        } else if (v.getId() == R.id.firefighter_pic){
            data.putExtra("imagename", "firefighter");
        } else if (v.getId() == R.id.police_pic){
            data.putExtra("imagename", "police");
        }else if (v.getId() == R.id.taxi_pic){
            data.putExtra("imagename", "taxi");
        }else if (v.getId() == R.id.image_pic){
            data.putExtra("imagename", "image");
        }
        setResult(RESULT_OK, data);
        finish();
    }
}
