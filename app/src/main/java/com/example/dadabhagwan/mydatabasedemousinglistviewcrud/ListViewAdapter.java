package com.example.dadabhagwan.mydatabasedemousinglistviewcrud;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.CustomAdapter.MyAdapter;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.Model.UserModel;

import java.util.ArrayList;

import static com.example.dadabhagwan.mydatabasedemousinglistviewcrud.R.id.lv;

public class ListViewAdapter extends Activity {
    ListView lv;
    ArrayList<UserModel> list=new ArrayList<UserModel>();
    DataBaseHandler db;
    SQLiteDatabase sqdb;
    Cursor cursor;
    MyAdapter adapter;
    ArrayList<String> fname_ArrayList = new ArrayList<String>();
    ArrayList<String> lname_ArrayList = new ArrayList<String>();
    ArrayList<String> age_ArrayList = new ArrayList<String>();
    ArrayList<String> edu_ArrayList = new ArrayList<String>();
    ArrayList<Integer> id_ArrayList=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_adapter);
        lv= (ListView) findViewById(R.id.lv1);
        db=new DataBaseHandler(this);


    }
    @Override
    protected void onResume() {

        showSqlDB();

        super.onResume();
    }


    public void showSqlDB()
    {
        sqdb=db.getWritableDatabase();
        cursor=sqdb.rawQuery("SELECT * FROM contacts",null);
        fname_ArrayList.clear();
        lname_ArrayList.clear();
        age_ArrayList.clear();
        edu_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                fname_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_FIRST_NAME)));

                lname_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_LAST_NAME)));

                age_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_AGE)));

                edu_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_EDUCATION)));

                id_ArrayList.add(cursor.getInt(cursor.getColumnIndex(DataBaseHandler.STUDENT_ID)));

            } while (cursor.moveToNext());
        }
        adapter=new MyAdapter(list,ListViewAdapter.this,fname_ArrayList,lname_ArrayList,age_ArrayList,edu_ArrayList,id_ArrayList);
        lv.setAdapter(adapter);


        cursor.close();
    }

}
