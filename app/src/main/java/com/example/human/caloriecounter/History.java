package com.example.human.caloriecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class History extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //saveButton code
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new backButtonClicked());

    }

    //Send user to Calorie Counter Screen
    public class backButtonClicked implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Create Intention
            Intent myIntent = new Intent(History.this, CalorieCountScreen.class);
            //Call to send
            History.this.startActivity(myIntent);
        }
    }
}
