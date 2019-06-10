package com.weidingqiang.skyworthui.tianjin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weidingqiang.skyworthui.R;

/**
 * Created by ist on 2017/7/6.
 */

public class ImageButton extends ViewGroup {
    private static final String TAG = ImageButton.class.getSimpleName();
    private int width = 0;
    private int height = 0;
//    private float zi = 2f;
	private float zi = 3f;
    private int mu = 4;

    private CharSequence text = "";
    private int src = -1;
    private int textcolor = -1;

    private ImageView img;
    private TextView textView;

    private int textsize;

    public void setBackGroundChecked() {
        textView.setTextColor(getResources().getColor(android.R.color.white));
//        postInvalidate();
    }

    public void setBackGroundUnChecked() {
        textView.setTextColor(Color.parseColor("#FFE0E0E0"));
//        postInvalidate();
    }

    public ImageButton(Context context) {
        super(context);
    }

    public ImageButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ImageButton);
        text = array.getText(R.styleable.ImageButton_text);
        src = array.getResourceId(R.styleable.ImageButton_src, R.drawable.encroid_progress);
        textsize = array.getDimensionPixelSize(R.styleable.ImageButton_text_size, 20);
        textcolor = array.getColor(R.styleable.ImageButton_text_color, getResources().getColor(android.R.color.white));
        array.recycle();

        img = new ImageView(context);
        img.setBackgroundResource(src);
       
        textView = new TextView(context);
        textView.setText(text);
        textView.setTextColor(textcolor);
        textView.setTextSize(9);

       
        addView(img);
        addView(textView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        //获取父view提供的大小和模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "width = " + width + " height = " + height + " widthMode = " + widthMode + " heightMode = " + heightMode);

        //初始化子view的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //重新设置子view的大小
        int img_width = (int) (width * zi / mu);
        int img_height = (int) (width * zi / mu);
        Log.e(TAG, "img_width = " + img_width + " img_height = " + img_height);
        int imgWidthMeasureSpec = MeasureSpec.makeMeasureSpec(img_width, MeasureSpec.EXACTLY);
        int imgHeightMeasureSpec = MeasureSpec.makeMeasureSpec(img_height, MeasureSpec.EXACTLY);
        getChildAt(0).measure(imgWidthMeasureSpec, imgHeightMeasureSpec);
// Log.d(TAG, "zhouzf--imgWidthMeasureSpec = " + imgWidthMeasureSpec + " imgHeightMeasureSpec = " + imgHeightMeasureSpec);

        int textWidth = getMeasuredWidth();
        int textHeight = ((getMeasuredHeight() - getChildAt(0).getMeasuredHeight())) / 2;
        int textWidthMeasureSpec = MeasureSpec.makeMeasureSpec(textWidth, MeasureSpec.AT_MOST);
        int textHeightMeasureSpec = MeasureSpec.makeMeasureSpec(textHeight, MeasureSpec.AT_MOST);
        Log.e(TAG, "textWidth = " + textWidth + " textHeight = " + textHeight);
        getChildAt(1).measure(textWidthMeasureSpec, textHeightMeasureSpec);

        //重新设置自己的大小
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    	
    	
        int textHeight = getChildAt(1).getMeasuredHeight();
        int textWidth = getChildAt(1).getMeasuredWidth();
        int imgHeight = getChildAt(0).getMeasuredHeight();
        int imgWidth = getChildAt(0).getMeasuredWidth();

        Log.e(TAG, "imgHeight =  " + imgHeight + " imgWidth = " + imgWidth + " textWidth = " + textWidth + " textHeight = " + textHeight);

        int left = (int) ((width - (width * zi / mu)) / 2);
        int top = (height - imgHeight - textHeight) / 3;
        int right = left + imgWidth;
        int bottom = top + imgHeight;
        getChildAt(0).layout(left, top, right, bottom);

        int textLeft = (width - textWidth) / 2;
        int textTop = bottom + top;
        int textRight = textLeft + textWidth;
        int textBottom = textTop + textHeight;
        Log.e(TAG, "bottom = " + bottom + " top = " + top + " textTop = " + textTop + " textBottom = " + textBottom);
        getChildAt(1).layout(textLeft, textTop, textRight, (textBottom+5));
    }

//    @SuppressLint("ResourceType")
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
//        Log.e(TAG,"onFocusChanged gainFocus = "+gainFocus + " direction = "+direction);
        if(gainFocus) {
            setScaleX(1.1f);
            setScaleY(1.1f);
           // setBackGroundChecked();
          //  setBackgroundResource(R.drawable.ist_shape_blue);
//            setBackgroundResource(R.drawable.app_yellow);
        }else
        {
            setScaleX(1f);
            setScaleY(1f);
            //setBackGroundUnChecked();
           // setBackgroundResource(R.drawable.ist_shape_gray);
//		        setBackgroundResource(Color.parseColor("0x00000000"));
//		        setBackgroundColor(getResources().getColor(R.color.tranlate));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            Toast.makeText(getContext(),"跳入子项",Toast.LENGTH_SHORT).show();
            //获取焦点
            requestFocus();
//            Log.e(TAG, "onTouchEvent action = " + event.getAction());
//            setScaleX(1.1f);
//            setScaleY(1.1f);
        }
		if(event.getAction() == MotionEvent.ACTION_UP) {
//			setScaleX(1f);
//            setScaleY(1f);
		}
        return super.onTouchEvent(event);
    }

}
