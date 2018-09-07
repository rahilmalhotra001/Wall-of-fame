package com.example.rahil.walloffame;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.*;
import java.util.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.widget.*;
import android.view.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.provider.MediaStore;
import android.widget.*;

public class ThirdActivity extends AppCompatActivity {
    private Button halloffame,addpost,home;
    private String namestore, classstore,sportsstore, positionstore,comment;
    private TextView tvcomment;
    private DBHelper mydb ;
    FourthActivity FA =new FourthActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent i = getIntent();
        mydb = new DBHelper(this);
        namestore = i.getStringExtra("name");
        classstore = i.getStringExtra("class");
        sportsstore = i.getStringExtra("sports");
        positionstore= i.getStringExtra("position");
        comment= i.getStringExtra("comment");
        halloffame=(Button)findViewById(R.id.halloffame);
        addpost=(Button)findViewById(R.id.addpost);
        home =(Button)findViewById(R.id.home);

        addpost.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(ThirdActivity.this);
                d.setContentView(R.layout.dialog_third);
                d.setTitle("Enter your comments");
                d.show();
                Window window = d.getWindow();
                window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                tvcomment=(TextView)d.findViewById(R.id.tvcomment);
                Button dialogbutton=(Button)d.findViewById(R.id.dialogbutton);

                dialogbutton.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        try {
                            String extra = tvcomment.getText().toString();
                            comment += extra;
                            mydb.insertdetails(mydb.numberOfRows() + 1, namestore, classstore, sportsstore, positionstore, comment);
                            /*Intent i;
                            if(FA.adapter==null)
                            i = new Intent(ThirdActivity.this, FourthActivity.class);
                            else
                            FA.adapter.notifyDataSetChanged();*/
                            Log.e("ThirdActivity", Integer.toString(mydb.numberOfRows()));
                            d.cancel();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        halloffame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
                startActivity(i);
            }
        });
        home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
