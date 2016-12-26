package com.example.dadabhagwan.mydatabasedemousinglistviewcrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dadabhagwan on 10/26/2016.
 */

public class background extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent set=getIntent();
        int position=set.getIntExtra("pos1",0);
        Intent my=new Intent(background.this,FirstActivity.class);
        my.putExtra("p",position);
        setResult(RESULT_OK,my);
        finish();

    }


}
