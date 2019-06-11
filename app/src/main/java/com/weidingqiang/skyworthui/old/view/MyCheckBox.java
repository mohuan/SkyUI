
package com.weidingqiang.skyworthui.old.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.CheckBox;


public class MyCheckBox extends CheckBox{
	protected static final String TAG = "MyCheckBox";

	private boolean isChecked = false;
	
	public MyCheckBox(Context context) {
		super(context);
	}

	public MyCheckBox(Context context, AttributeSet attrs) {
	  	super(context,attrs);
	}

	@Override
	public boolean isChecked() {
	  	return super.isChecked();
	}
	
	@Override
	public void setChecked(boolean checked) {
		isChecked = checked;
        super.setChecked(checked);
    }
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	//Log.i(TAG, "xun keyCode:"+keyCode +"  isChecked= "+isChecked);

		if(keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			if(isChecked){
				this.setChecked(false);
			}else{
				this.setChecked(true);
			}
			
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

