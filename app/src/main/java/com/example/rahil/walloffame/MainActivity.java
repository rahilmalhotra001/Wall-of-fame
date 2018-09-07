package com.example.rahil.walloffame;

import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private EditText name, age,house;
    private Spinner s1,s2,s3;
    private Button button;
    private String namestore, agestore, classstore, sportsstore, teamstore, housestore;
    ArrayList<String> studentclass =new ArrayList<String>();
    ArrayList<String> studentsports =new ArrayList<String>();
    ArrayList<String> studentteam =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        house = (EditText) findViewById(R.id.house);
        button = (Button) findViewById(R.id.button);

        s1=(Spinner)findViewById(R.id.classname);
        s1.setOnItemSelectedListener(this);
        studentclass.add("Select class");
        studentclass.add("1A");
        studentclass.add("1B");
        studentclass.add("2A");
        studentclass.add("2B");
        studentclass.add("3A");
        studentclass.add("3B");
        studentclass.add("4A");
        studentclass.add("4B");
        studentclass.add("5A");
        studentclass.add("5B");
        ArrayAdapter<String> dataAdapter =new ArrayAdapter<String>(this,R.layout.spinnerclass,R.id.textView1,studentclass);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        s1.setAdapter(dataAdapter);

        s2=(Spinner)findViewById(R.id.sports);
        s2.setOnItemSelectedListener(this);
        studentsports.add("Select sports");
        studentsports.add("Cricket");
        studentsports.add("Football");
        studentsports.add("Basketball");
        studentsports.add("Hockey");
        studentsports.add("Volleyball");
        studentsports.add("Tennis");
        ArrayAdapter<String> dataAdapter1 =new ArrayAdapter<String>(this,R.layout.spinnersports,R.id.textView1,studentsports);
        dataAdapter1.setDropDownViewResource(R.layout.spinner_item);
        s2.setAdapter(dataAdapter1);

        s3=(Spinner)findViewById(R.id.team);
        s3.setOnItemSelectedListener(this);
        studentteam.add("Select category");
        studentteam.add("Sub-Junior");
        studentteam.add("Junior");
        studentteam.add("Senior");
        ArrayAdapter<String> dataAdapter2 =new ArrayAdapter<String>(this,R.layout.spinnerteam,R.id.textView1,studentteam);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_item);
        s3.setAdapter(dataAdapter2);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                namestore=name.getText().toString();
                agestore =age.getText().toString();
                housestore=house.getText().toString();
                if(namestore.length()==0)
                    name.setError("You cannot leave name space blank");
                else if(agestore.length()==0)
                    age.setError("You cannot leave age space blank");
                else if(house.length()==0)
                    house.setError("You cannot leave house space blank");
                else if(classstore.equals("Select class"))
                    Toast.makeText(getApplicationContext(), "Select a class before proceeding", Toast.LENGTH_SHORT).show();
                else if(sportsstore.equals("Select sports"))
                    Toast.makeText(getApplicationContext(), "Select a sport before proceeding", Toast.LENGTH_SHORT).show();
                else if(teamstore.equals("Select category"))
                    Toast.makeText(getApplicationContext(), "Select a category before proceeding", Toast.LENGTH_SHORT).show();
                else
                {
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("name", namestore);
                    i.putExtra("age", agestore);
                    i.putExtra("class", classstore);
                    i.putExtra("sports", sportsstore);
                    i.putExtra("category", teamstore);
                    i.putExtra("house", housestore);
                    startActivity(i);

                }
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item=parent.getItemAtPosition(position).toString();
        Spinner spin = (Spinner)parent;
        if(spin.getId() == R.id.classname)
            classstore=item;

        if(spin.getId() == R.id.sports)
            sportsstore=item;

        if(spin.getId() == R.id.team)
            teamstore=item;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
