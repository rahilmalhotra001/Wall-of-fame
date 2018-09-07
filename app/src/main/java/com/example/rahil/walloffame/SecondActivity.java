package com.example.rahil.walloffame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.*;
import java.util.*;
import java.util.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.widget.*;
import android.view.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.app.*;
import android.content.*;
import android.os.*;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.*;
import android.view.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    ArrayList<String> studentposition =new ArrayList<String>();
    private CheckBox cb1,cb2,cb3,cb4;
    private EditText score;
    private Spinner s1;
    private String namestore,agestore, classstore,sportsstore, teamstore,housestore, levelstore, positionstore="", scorestore;
    private Button savebt,mailbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i=getIntent();
        namestore = i.getStringExtra("name");
        agestore = i.getStringExtra("age");
        classstore = i.getStringExtra("class");
        sportsstore = i.getStringExtra("sports");
        teamstore = i.getStringExtra("category");
        housestore = i.getStringExtra("house");


        s1=(Spinner)findViewById(R.id.position);
        s1.setOnItemSelectedListener(this);
        s1.setOnItemSelectedListener(this);
        studentposition.add("Select Position");
        studentposition.add("First");
        studentposition.add("Second");
        studentposition.add("Third");
        studentposition.add("Fourth");
        ArrayAdapter<String> dataAdapter =new ArrayAdapter<String>(this,R.layout.spinnerposition,R.id.textView1,studentposition);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        s1.setAdapter(dataAdapter);


        score=(EditText)findViewById(R.id.score);
        cb1=(CheckBox)findViewById(R.id.cb1);
        cb2=(CheckBox)findViewById(R.id.cb2);
        cb3=(CheckBox)findViewById(R.id.cb3);
        cb4=(CheckBox)findViewById(R.id.cb4);
        savebt=(Button)findViewById(R.id.savebt);
        mailbt=(Button)findViewById(R.id.mailbt);


        savebt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int a=cb1.isChecked()?1:0;int b=cb2.isChecked()?1:0;int c=cb3.isChecked()?1:0;int d=cb4.isChecked()?1:0;
                scorestore = score.getText().toString();
                if(a+b+c+d==0)
                    Toast.makeText(getApplicationContext(), "Please check a box", Toast.LENGTH_SHORT).show();
                else if(positionstore.length()==0)
                    Toast.makeText(getApplicationContext(), "Select a Postion before saving", Toast.LENGTH_SHORT).show();
                else if(score.length()==0)
                    score.setError("Enter a score");
                else
                {
                    if(cb1.isChecked())
                        levelstore="International";
                    if(cb2.isChecked())
                        levelstore="National";
                    if(cb3.isChecked())
                        levelstore="State";
                    if(cb4.isChecked())
                        levelstore="District";
                    Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                    i.putExtra("name", namestore);
                    i.putExtra("class", classstore);
                    i.putExtra("sports", sportsstore);
                    i.putExtra("position", positionstore);
                    i.putExtra("comment", namestore +" of class "+classstore +" has achieved great heights in " + sportsstore +" on a "+ levelstore+" level"+" at the young age of " + agestore + " in " + teamstore + " division.\n\nHe got a score of "+scorestore+" and has secured "+positionstore +" position.\n\n Many congratulations!\n\n");
                    startActivity(i);
                }
            }
        });

        mailbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int a=cb1.isChecked()?1:0;int b=cb2.isChecked()?1:0;int c=cb3.isChecked()?1:0;int d=cb4.isChecked()?1:0;
                scorestore = score.getText().toString();
                if(a+b+c+d==0)
                    Toast.makeText(getApplicationContext(), "Please check a box", Toast.LENGTH_SHORT).show();
                else if(positionstore.length()==0)
                    Toast.makeText(getApplicationContext(), "Select a Postion before saving", Toast.LENGTH_SHORT).show();
                else if(score.length()==0)
                    score.setError("Enter a score");
                else
                {
                    if(cb1.isChecked())
                        levelstore="International";
                    if(cb2.isChecked())
                        levelstore="National";
                    if(cb3.isChecked())
                        levelstore="State";
                    if(cb4.isChecked())
                        levelstore="District";
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Achievement of " + namestore);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Sir,\n\nI am happy to tell you that " + namestore +" of class "+classstore +" has achieved great heights in " + sportsstore +" on a "+ levelstore+" level"+" at the young age of " + agestore + " in " + teamstore + " division.\n\nHe got a score of "+scorestore+" and has secured "+positionstore +" position.\n\n Many congratulations!");
                    startActivity(emailIntent);
                }
            }
        });

    }
    public void onCheckBoxClicked(View v)
    {
        if(v.getId()==R.id.cb1)
        {
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
        }
        else if(v.getId()==R.id.cb2)
        {
            cb1.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
        }
        else if(v.getId()==R.id.cb3)
        {
            cb1.setChecked(false);
            cb2.setChecked(false);
            cb4.setChecked(false);
        }
        else if(v.getId()==R.id.cb4)
        {
            cb1.setChecked(false);
            cb2.setChecked(false);
            cb3.setChecked(false);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item=parent.getItemAtPosition(position).toString();
        if(!item.equals("Select Position"))
            positionstore=item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
