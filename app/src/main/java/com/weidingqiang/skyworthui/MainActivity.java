package com.weidingqiang.skyworthui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weidingqiang.skyworthui.old.setting.MainSetingsActivity;
import com.weidingqiang.skyworthui.tianjin.setting.TJMainSettingsActivity;

/**
 * 电视设备的 dpi 是 240
 * 对应的文件为
 * 160dpi ~ 240dpi	hdpi
 * 240dpi ~ 320dpi	xhdpi
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button setting_but_old;
    private Button setting_but_tj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting_but_old = (Button) this.findViewById(R.id.setting_but_old);
        setting_but_tj = (Button) this.findViewById(R.id.setting_but_tj);
        setting_but_old.setOnClickListener(this);
        setting_but_tj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_but_old:
                startActivity(MainSetingsActivity.newInstance(getApplicationContext()));
                break;
            case R.id.setting_but_tj:
                startActivity(TJMainSettingsActivity.newInstance(getApplicationContext()));
                break;
        }
    }
}
