package com.example.rahil.walloffame;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.util.Log;
import android.widget.*;
import android.view.*;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.View;
import android.content.Intent;

public class FourthActivity extends AppCompatActivity{

    private ListView layoutfourth_lt;
    public static LazyAdapter adapter;
    private ArrayList<HashMap<String,String>> array_list=new ArrayList<>();
    private DBHelper mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Intent i = getIntent();
        mydb = new DBHelper(this);
        array_list=mydb.getAllDetails();
        layoutfourth_lt=(ListView)findViewById(R.id.layoutfourth_lt);
        adapter = new LazyAdapter(FourthActivity.this, array_list);
        layoutfourth_lt.setAdapter(adapter);
    }

    public class LazyAdapter extends BaseAdapter {

        private Activity activity;
        private ArrayList<HashMap<String, String>> data;
        private LayoutInflater inflater=null;


        public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
            activity = a;
            data=d;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View vi=convertView;
            if(data.get(position).get("position").equals("First"))
            vi = inflater.inflate(R.layout.layout_fourth1, null);
            else if(data.get(position).get("position").equals("Second"))
            vi = inflater.inflate(R.layout.layout_fourth2, null);
            else if(data.get(position).get("position").equals("Third"))
            vi = inflater.inflate(R.layout.layout_fourth3, null);
            else
            vi = inflater.inflate(R.layout.layout_fourth, null);


            TextView name= (TextView)vi.findViewById(R.id.name);
            TextView classs= (TextView)vi.findViewById(R.id.classs);
            TextView sports= (TextView)vi.findViewById(R.id.sports);
            TextView positions= (TextView)vi.findViewById(R.id.position);
            TextView comments=(TextView)vi.findViewById(R.id.comments);

            name.setText(data.get(position).get("name"));
            classs.setText(data.get(position).get("class"));
            sports.setText(data.get(position).get("sports"));
            positions.setText(data.get(position).get("position"));
            comments.setText(data.get(position).get("comments"));
            return vi;
        }
    }
}