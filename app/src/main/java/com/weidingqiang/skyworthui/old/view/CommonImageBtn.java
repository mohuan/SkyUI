package com.weidingqiang.skyworthui.old.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weidingqiang.skyworthui.R;


public class CommonImageBtn extends LinearLayout {

	private ImageView imageView;
	private TextView  textView;
	
	public CommonImageBtn(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public CommonImageBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//inflater.inflate(R.layout.ist_commonbutton, this);
		View.inflate(context, R.layout.ist_commonbutton, this);
		
		imageView=(ImageView) findViewById(R.id.imageView);
		textView=(TextView)findViewById(R.id.textView);
	}

    public void setImageResource(int resId) {  
        imageView.setImageResource(resId);  
    }  

    public void setTextViewText(String text) {  
        textView.setText(text);  
    }  
	
	public void setSelected(boolean state) {
		imageView.setSelected(state);
	}

}

