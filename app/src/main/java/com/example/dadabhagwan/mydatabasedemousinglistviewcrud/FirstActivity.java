package com.example.dadabhagwan.mydatabasedemousinglistviewcrud;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.CustomAdapter.MyAdapter;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends Activity {

    ListView lv;
    Button btnNext;
    java.util.List<UserModel> listdata;
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
        setContentView(R.layout.activity_first);
        initialize();
        db=new DataBaseHandler(FirstActivity.this);


        showSqlDB();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1=new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(i1,1);


            }
        });
//        showDB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i2=new Intent(FirstActivity.this,ListViewAdapter.class);
//                startActivity(i2);
//            }
//        });

    }
    public void initialize()
    {
        lv= (ListView) findViewById(R.id.lv);
        btnNext= (Button) findViewById(R.id.btnNext);
      //  showDB= (Button) findViewById(R.id.show);


    }

    public void showSqlDB()
    {
    sqdb=db.getWritableDatabase();
    cursor=sqdb.rawQuery("SELECT * FROM contacts",null);
    fname_ArrayList.clear();
    lname_ArrayList.clear();
    age_ArrayList.clear();
    edu_ArrayList.clear();
    id_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                id_ArrayList.add(cursor.getInt(cursor.getColumnIndex(DataBaseHandler.STUDENT_ID)));

                fname_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_FIRST_NAME)));

                lname_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_LAST_NAME)));

                age_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_AGE)));

                edu_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHandler.STUDENT_EDUCATION)));

            } while (cursor.moveToNext());
        }


        //db.showSqlDB();
        adapter=new MyAdapter(list,FirstActivity.this,fname_ArrayList,lname_ArrayList,age_ArrayList,edu_ArrayList,id_ArrayList);

        lv.setAdapter(adapter);
        cursor.close();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1) {
            if(data!=null) {

               UserModel um=data.getParcelableExtra("key1");
                db.addStudent(um);
                showSqlDB();
                adapter.notifyDataSetChanged();
            }
        }
        else if(requestCode==100) {
            if (resultCode == RESULT_OK) {
                if (data != null) {



                    UserModel o1 = data.getParcelableExtra("key1");

                    db.updateContact(o1);
                    showSqlDB();
                    adapter.notifyDataSetChanged();
                }
            }
        }
        else if(requestCode==200) {
            if (resultCode == RESULT_OK) {
                if (data != null) {



                    UserModel id=new UserModel();
                    int p=data.getIntExtra("p",0);
                    id.setId(p);
                    db.deleteContact(id);
                    showSqlDB();
                    adapter.notifyDataSetChanged();
                }
            }
        }


    }}
