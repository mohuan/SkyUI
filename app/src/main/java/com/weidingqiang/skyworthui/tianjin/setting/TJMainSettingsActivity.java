package com.weidingqiang.skyworthui.tianjin.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weidingqiang.skyworthui.R;
import com.weidingqiang.skyworthui.old.setting.MainSetingsActivity;

public class TJMainSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjmain_settings);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, TJMainSettingsActivity.class);
        return intent;
    }
}
