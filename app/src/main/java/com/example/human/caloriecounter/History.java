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

    Button backButton;
    //ArrayList<String> myArr = new ArrayList<String>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //saveButton code
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new backButtonClicked());



        lv = (ListView) findViewById(R.id.historyListView);
        List<String> myArr = (ArrayList<String>) getIntent().getSerializableExtra("myArr");

        myArr.add("4/7/2016: 415");
        myArr.add("4/6/2016: 523");
        myArr.add("4/5/2016: 644");
        myArr.add("4/4/2016: 1445");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArr );

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
