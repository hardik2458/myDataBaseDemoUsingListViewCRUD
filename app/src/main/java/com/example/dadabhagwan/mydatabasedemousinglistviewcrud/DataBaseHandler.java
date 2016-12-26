package com.example.dadabhagwan.mydatabasedemousinglistviewcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agile on 24-Oct-16.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "studentDataBase";

    // Contacts table name
    public static final String STUDENT_TABLE = "contacts";


    // Contacts Table Columns names
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_FIRST_NAME = "fname";
    public static final String STUDENT_LAST_NAME = "lname";
    public static final String STUDENT_AGE = "age";
    public static final String STUDENT_EDUCATION = "edu";

    ArrayList<String> fname_ArrayList = new ArrayList<String>();
    ArrayList<String> lname_ArrayList = new ArrayList<String>();
    ArrayList<String> age_ArrayList = new ArrayList<String>();
    ArrayList<String> edu_ArrayList = new ArrayList<String>();
    ArrayList<Integer> id_ArrayList = new ArrayList<Integer>();
    DataBaseHandler db;
    SQLiteDatabase sqdb;
    Cursor cursor;
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + STUDENT_TABLE + "("
                + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + STUDENT_FIRST_NAME + " TEXT,"
                + STUDENT_LAST_NAME + " TEXT," + STUDENT_AGE + " TEXT," + STUDENT_EDUCATION + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addStudent(UserModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_FIRST_NAME, student.getFirstName());
        values.put(STUDENT_LAST_NAME, student.getLastName());
        values.put(STUDENT_AGE, student.getAge());
        values.put(STUDENT_EDUCATION, student.getQualification());


        // Inserting Row
        db.insert(STUDENT_TABLE, null, values);
        db.close(); // Closing database connection
    }


    // Updating single contact
    public int updateContact(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_FIRST_NAME, userModel.getFirstName());
        values.put(STUDENT_LAST_NAME, userModel.getLastName());
        values.put(STUDENT_AGE, userModel.getAge());
        values.put(STUDENT_EDUCATION, userModel.getQualification());

        // updating row
        return db.update(STUDENT_TABLE, values, STUDENT_ID + " = ?", new String[]{String.valueOf(userModel.getId())});
    }


    // Deleting single contact
    public void deleteContact(UserModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE, STUDENT_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public void showSqlDB() {
        sqdb=this.getWritableDatabase();
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
        cursor.close();
    }
}