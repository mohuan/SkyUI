/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weidingqiang.skyworthui.old.navigationbar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;


import com.weidingqiang.skyworthui.R;

import static android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK;
import static android.view.accessibility.AccessibilityNodeInfo.ACTION_LONG_CLICK;

//import android.view.HardwareCanvas;

public class KeyButtonView extends ImageView {
    private static final String TAG = "xun navigationbar.KeyButtonView";
    private static final boolean DEBUG = false;

	private final float TEXTSIZE = 22.0f;
	private final float SRC_OFFSET = -10.0f;
	private final float TEXT_OFFSET = -5.0f;
	private String _text = null;
	private int _color = Color.WHITE;
	private float _textsize = TEXTSIZE;
	
	public static final boolean b_NavigationBarText = false;//SystemProperties.get("ro.navbar.show.text","off").equals("on");

    // TODO: Get rid of this
    public static final float DEFAULT_QUIESCENT_ALPHA = 1f;

    private long mDownTime;
    private int mCode;
    private int mTouchSlop;
    private float mDrawingAlpha = 1f;
    private float mQuiescentAlpha = DEFAULT_QUIESCENT_ALPHA;
    private boolean mSupportsLongpress = true;
    private AudioManager mAudioManager;
    private Animator mAnimateToQuiescent = new ObjectAnimator();

    private final Runnable mCheckLongPress = new Runnable() {
        public void run() {
            if (isPressed()) {
                // Log.d("KeyButtonView", "longpressed: " + this);
                if (isLongClickable()) {
                    // Just an old-fashioned ImageView
                    performLongClick();
                } else {
                    sendEvent(KeyEvent.ACTION_DOWN, KeyEvent.FLAG_LONG_PRESS);
                    sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_LONG_CLICKED);
                }
            }
        }
    };

    public KeyButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.KeyButtonView,
                defStyle, 0);

        mCode = a.getInteger(R.styleable.KeyButtonView_keyCode, 0);

        mSupportsLongpress = a.getBoolean(R.styleable.KeyButtonView_keyRepeat, true);

        setDrawingAlpha(mQuiescentAlpha);

        a.recycle();

		if(b_NavigationBarText){
			setScaleType(ScaleType.MATRIX);
			Matrix matrix = new Matrix();
			matrix.setTranslate(0.0f, SRC_OFFSET);
			setImageMatrix(matrix);
		}
				
        setClickable(true);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
       // setBackground(new KeyButtonRipple(context, this));
    }
	
    @Override
    public void setImageMatrix(Matrix matrix) {
    	//Matrix matrix = new Matrix();
		matrix.setTranslate(0.0f, SRC_OFFSET);
        super.setImageMatrix(matrix);

    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        if (mCode != 0) {
            info.addAction(new AccessibilityNodeInfo.AccessibilityAction(ACTION_CLICK, null));
            if (mSupportsLongpress) {
                info.addAction(
                        new AccessibilityNodeInfo.AccessibilityAction(ACTION_LONG_CLICK, null));
            }
        }
    }

    @Override
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (action == ACTION_CLICK && mCode != 0) {
            sendEvent(KeyEvent.ACTION_DOWN, 0, SystemClock.uptimeMillis());
            sendEvent(KeyEvent.ACTION_UP, 0);
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
            playSoundEffect(SoundEffectConstants.CLICK);
            return true;
        } else if (action == ACTION_LONG_CLICK && mCode != 0 && mSupportsLongpress) {
            sendEvent(KeyEvent.ACTION_DOWN, KeyEvent.FLAG_LONG_PRESS);
            sendEvent(KeyEvent.ACTION_UP, 0);
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_LONG_CLICKED);
            return true;
        }
        return super.performAccessibilityAction(action, arguments);
    }

    public void setQuiescentAlpha(float alpha, boolean animate) {
        mAnimateToQuiescent.cancel();
        alpha = Math.min(Math.max(alpha, 0), 1);
        if (alpha == mQuiescentAlpha && alpha == mDrawingAlpha) return;
        mQuiescentAlpha = alpha;
//        if (DEBUG) Log.d(TAG, "New quiescent alpha = " + mQuiescentAlpha);
        if (animate) {
            mAnimateToQuiescent = animateToQuiescent();
            mAnimateToQuiescent.start();
        } else {
            setDrawingAlpha(mQuiescentAlpha);
        }
    }

    private ObjectAnimator animateToQuiescent() {
        return ObjectAnimator.ofFloat(this, "drawingAlpha", mQuiescentAlpha);
    }

    public float getQuiescentAlpha() {
        return mQuiescentAlpha;
    }

    public float getDrawingAlpha() {
        return mDrawingAlpha;
    }

    public void setDrawingAlpha(float x) {
        setImageAlpha((int) (x * 255));
        mDrawingAlpha = x;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        int x, y;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //Log.d("KeyButtonView", "press");
                mDownTime = SystemClock.uptimeMillis();
                setPressed(true);
                if (mCode != 0) {
                    sendEvent(KeyEvent.ACTION_DOWN, 0, mDownTime);
                } else {
                    // Provide the same haptic feedback that the system offers for virtual keys.
                    performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                }
                if (mSupportsLongpress) {
                    removeCallbacks(mCheckLongPress);
                    postDelayed(mCheckLongPress, ViewConfiguration.getLongPressTimeout());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                x = (int)ev.getX();
                y = (int)ev.getY();
                setPressed(x >= -mTouchSlop
                        && x < getWidth() + mTouchSlop
                        && y >= -mTouchSlop
                        && y < getHeight() + mTouchSlop);
                break;
            case MotionEvent.ACTION_CANCEL:
                setPressed(false);
                if (mCode != 0) {
                    sendEvent(KeyEvent.ACTION_UP, KeyEvent.FLAG_CANCELED);
                }
                if (mSupportsLongpress) {
                    removeCallbacks(mCheckLongPress);
                }
                break;
            case MotionEvent.ACTION_UP:
                final boolean doIt = isPressed();
                setPressed(false);
                if (mCode != 0) {
                    if (doIt) {
                        sendEvent(KeyEvent.ACTION_UP, 0);
                        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
                        playSoundEffect(SoundEffectConstants.CLICK);
                    } else {
                        sendEvent(KeyEvent.ACTION_UP, KeyEvent.FLAG_CANCELED);
                    }
                } else {
                    // no key code, just a regular ImageView
                    if (doIt) {
                        performClick();
                    }
                }
                if (mSupportsLongpress) {
                    removeCallbacks(mCheckLongPress);
                }
                break;
        }

        return true;
    }

    public void playSoundEffect(int soundConstant) {
//        mAudioManager.playSoundEffect(soundConstant, ActivityManager.getCurrentUser());
    };

    public void sendEvent(int action, int flags) {
        sendEvent(action, flags, SystemClock.uptimeMillis());
    }

    void sendEvent(int action, int flags, long when) {
//        final int repeatCount = (flags & KeyEvent.FLAG_LONG_PRESS) != 0 ? 1 : 0;
//        final KeyEvent ev = new KeyEvent(mDownTime, when, action, mCode, repeatCount,
//                0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
//                flags | KeyEvent.FLAG_FROM_SYSTEM | KeyEvent.FLAG_VIRTUAL_HARD_KEY,
//                InputDevice.SOURCE_KEYBOARD);
//        InputManager.getInstance().injectInputEvent(ev,
//                InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);
    }

	public void setText(String text){
        this._text = text;
    }
    
    public void setColor(int color){
        this._color = color;
    }
    
    public void setTextSize(float textsize){
        this._textsize = textsize;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
		
		if(_text != null){
	        Paint paint = new Paint();
	        paint.setTextAlign(Align.CENTER);
	        paint.setColor(_color);
	        paint.setTextSize(_textsize);
	        canvas.drawText(_text, canvas.getWidth()/2, (canvas.getHeight())+ TEXT_OFFSET, paint);
		}
    }
}


