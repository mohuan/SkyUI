package com.weidingqiang.skyworthui.old.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weidingqiang.skyworthui.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ist_slidemenuview);
    }


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MenuActivity.class);
        return intent;
    }
}
