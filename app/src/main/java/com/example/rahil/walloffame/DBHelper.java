package com.example.rahil.walloffame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "walloffame";
    public static final String COLUMN_Index = "ind";
    public static final String COLUMN_Name = "name";
    public static final String COLUMN_Class = "class";
    public static final String COLUMN_Sport = "sport";
    public static final String COLUMN_Position = "position";
    public static final String COLUMN_Comments = "comments";
    private int counter=1;
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table walloffame " + "(ind Integer primary key,name text,class text, sport text, position text, comments text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS walloffame");
        onCreate(db);
    }

    public boolean insertdetails (int index,String name,String classs,String sport, String position,String comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_Index, index);
        contentValues.put(COLUMN_Name, name);
        contentValues.put(COLUMN_Class, classs);
        contentValues.put(COLUMN_Sport, sport);
        contentValues.put(COLUMN_Position, position);
        contentValues.put(COLUMN_Comments, comments);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int index) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from walloffame where ind="+index+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updatedetails (int index,String name,String classs,String sport, String position,String comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_Index, index);
        contentValues.put(COLUMN_Name, name);
        contentValues.put(COLUMN_Class, classs);
        contentValues.put(COLUMN_Sport, sport);
        contentValues.put(COLUMN_Position, position);
        contentValues.put(COLUMN_Comments, comments);
        db.update(TABLE_NAME, contentValues, "ind = ? ", new String[] { Integer.toString(index) } );
        return true;
    }

    public Integer deletedetails(int index) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("walloffame","ind = ? ",new String[] { Integer.toString(index) });
    }

    public ArrayList<HashMap<String,String>> getAllDetails() {
        ArrayList<HashMap<String,String>> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from walloffame", null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            HashMap<String,String>hm=new HashMap<>();
            hm.put("name",res.getString(res.getColumnIndex(COLUMN_Name)));
            hm.put("class",res.getString(res.getColumnIndex(COLUMN_Class)));
            hm.put("sports",res.getString(res.getColumnIndex(COLUMN_Sport)));
            hm.put("position",res.getString(res.getColumnIndex(COLUMN_Position)));
            hm.put("comments",res.getString(res.getColumnIndex(COLUMN_Comments)));
            array_list.add(hm);
            res.moveToNext();
        }
        return array_list;
    }
}