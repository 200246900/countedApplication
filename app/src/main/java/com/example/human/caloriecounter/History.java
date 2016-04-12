package com.example.human.caloriecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    //Back Button
    Button backButton;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Back Button code
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new backButtonClicked());

        //Create Listview
        lv = (ListView) findViewById(R.id.historyListView);
        List<String> myArr = (ArrayList<String>) getIntent().getSerializableExtra("myArr");

        //Add some records for testing and viewing
        myArr.add("4/7/2016: 415");
        myArr.add("4/6/2016: 523");
        myArr.add("4/5/2016: 644");
        myArr.add("4/4/2016: 1445");

        //COnvert array to list item
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArr );

        //Set adapter
        lv.setAdapter(arrayAdapter);
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
