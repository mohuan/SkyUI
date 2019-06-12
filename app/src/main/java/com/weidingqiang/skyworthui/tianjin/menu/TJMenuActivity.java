package com.weidingqiang.skyworthui.tianjin.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weidingqiang.skyworthui.R;

public class TJMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ist_slidemenuview_tj);
    }


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, TJMenuActivity.class);
        return intent;
    }
}
