package com.example.dadabhagwan.mydatabasedemousinglistviewcrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.Model.UserModel;

public class SecondActivity extends Activity {

    EditText etFirstName,etLastName,etAge,etEducation;
    Button Save;
    Intent set;
    String fn,ln,ag,ed;
    int position;
    String fname,lname,age,education;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
        updatedata();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etFirstName.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "First Name cannot be Blank", Toast.LENGTH_LONG).show();
                    etFirstName.setError("First Name cannot be Blank");
                    return;
                }
                else
                {
                    fname = etFirstName.getText().toString();
                }

                if(etLastName.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Last Name cannot be Blank", Toast.LENGTH_LONG).show();
                    etLastName.setError("Last Name cannot be Blank");
                    return;
                }
                else
                {
                     lname=etLastName.getText().toString();
                }

                if(etAge.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Age cannot be Blank", Toast.LENGTH_LONG).show();
                    etAge.setError("Age cannot be Blank");
                    return;
                }
                else
                {
                    age=etAge.getText().toString();
                }

                if(etEducation.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Qualification cannot be Blank", Toast.LENGTH_LONG).show();
                    etEducation.setError("Qualification cannot be Blank");
                    return;
                }
                else
                {
                    education=etEducation.getText().toString();
                }

                UserModel data=new UserModel();
                data.setFirstName(fname);
                data.setLastName(lname);
                data.setAge(age);
                data.setQualification(education);
                data.setId(position);
                Intent my=new Intent(SecondActivity.this,FirstActivity.class);
                my.putExtra("key1",data);
         //       my.putExtra("pos1",position);
                setResult(RESULT_OK,my);
                finish();

            }
        });

    }

    public void initialize()
    {
        etFirstName= (EditText) findViewById(R.id.etFirstName);
        etLastName= (EditText) findViewById(R.id.etLastName);
        etAge= (EditText) findViewById(R.id.etAge);
        etEducation= (EditText) findViewById(R.id.etEducation);
        Save= (Button) findViewById(R.id.btnSave);

    }

    public void updatedata()
    {

        set=getIntent();
        fn=set.getStringExtra("fname");
        ln=set.getStringExtra("lname");
        ag=set.getStringExtra("age");
        ed=set.getStringExtra("edu");
        position=set.getIntExtra("pos",0);
        etFirstName.setText(fn);
        etLastName.setText(ln);
        etAge.setText(ag);
        etEducation.setText(ed);

        Toast.makeText(this, position+""+fn+"\n"+ln, Toast.LENGTH_SHORT).show();

    }

}
