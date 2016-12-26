package com.example.dadabhagwan.mydatabasedemousinglistviewcrud.CustomAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.DataBaseHandler;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.FirstActivity;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.Model.UserModel;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.R;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.SecondActivity;
import com.example.dadabhagwan.mydatabasedemousinglistviewcrud.background;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dadabhagwan on 10/24/2016.
 */

public class MyAdapter extends BaseAdapter{
    Activity context;

    LayoutInflater inflater;
    ArrayList<UserModel> data;
    DataBaseHandler db;

    ArrayList<String> fname;
    ArrayList<String> lname;
    ArrayList<String> age;
    ArrayList<String> edu ;
    ArrayList<Integer> id;

    public MyAdapter(ArrayList<UserModel> list,Activity c,ArrayList<String> fname,ArrayList<String> lname,ArrayList<String> age,ArrayList<String> edu,ArrayList<Integer> id) {
        this.data=list;
        this.context=c;
        this.fname=fname;
        this.lname=lname;
        this.age=age;
        this.edu=edu;
        this.id=id;
        db=new DataBaseHandler(context);


    }


    @Override
    public int getCount() {
        return fname.size();
    }

    @Override
    public UserModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View vi=view;
        final ViewHolder holder ;
        if(vi==null) {
            holder=new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi= inflater.inflate(R.layout.show,null);
            holder.FirstName= (TextView) vi.findViewById(R.id.tv1fname);
            holder.LastName= (TextView) vi.findViewById(R.id.tv2lname);
            holder.Age= (TextView) vi.findViewById(R.id.tv3age);
            holder.Education= (TextView) vi.findViewById(R.id.tv4edu);
            holder.Update= (Button) vi.findViewById(R.id.update);
            holder.Delete= (Button) vi.findViewById(R.id.Delete);
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag();


        }
        holder.FirstName.setText(fname.get(i));
        holder.LastName.setText(lname.get(i));
        holder.Age.setText(age.get(i));
        holder.Education.setText(edu.get(i));
        final int db_id=id.get(i);
        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send=new Intent(context,SecondActivity.class);
                send.putExtra("fname", fname.get(i));
                send.putExtra("lname",lname.get(i));
                send.putExtra("age",age.get(i));
                send.putExtra("edu",edu.get(i));
                send.putExtra("pos",db_id);
                context.startActivityForResult(send,100);
            }
        });

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder adb=new AlertDialog.Builder(context);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + i);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent delete=new Intent(context,background.class);
                        delete.putExtra("pos1",db_id);
                        context.startActivityForResult(delete,200);
                    }});
                adb.show();

            }
        });

        return vi;
    }
    public static class ViewHolder
    {
        public TextView FirstName,LastName,Age,Education;
        public Button Update,Delete;

    }


}


