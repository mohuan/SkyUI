package com.weidingqiang.skyworthui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weidingqiang.skyworthui.setting.MainSetingsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button setting_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting_but = (Button) this.findViewById(R.id.setting_but);
        setting_but.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_but:
                startActivity(MainSetingsActivity.newInstance(getApplicationContext()));
                break;
        }
    }
}
