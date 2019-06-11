package com.weidingqiang.skyworthui.old.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weidingqiang.skyworthui.R;
import com.weidingqiang.skyworthui.tianjin.setting.TJMainSettingsActivity;

public class NavigationBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, NavigationBarActivity.class);
        return intent;
    }
}
