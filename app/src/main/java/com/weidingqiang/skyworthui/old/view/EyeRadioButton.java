package com.weidingqiang.skyworthui.old.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RadioButton;


/**
 * Created by zeng on 2018/3/21.
 */

public class EyeRadioButton extends RadioButton {
    protected static final String TAG = "EyeRadioButton";

    public EyeRadioButton(Context context) {
        super(context);
    }

    public EyeRadioButton(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean isChecked() {
        return super.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {

        super.setChecked(checked);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
//            if(IstEventManager.MODE_BL_CUS.equals(IstEventManager.getInstance().getBrightnessMode())){
//                this.setChecked(true);
//            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


}
