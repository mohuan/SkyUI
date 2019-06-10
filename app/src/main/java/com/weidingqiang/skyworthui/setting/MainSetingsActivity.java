package com.weidingqiang.skyworthui.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weidingqiang.skyworthui.R;

public class MainSetingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setings);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainSetingsActivity.class);
        return intent;
    }
}
