package com.weidingqiang.skyworthui.tianjin.mainsoucesetting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private Context mContext;
    private View mConverView;
    private static int mPosition;
    private SparseArray<View> mViewSparseArray;
    public ViewHolder(Context context, int layout, ViewGroup parent, int position){
        mContext = context;
        mConverView = LayoutInflater.from(mContext).inflate(layout,parent,false);
        mViewSparseArray = new SparseArray<>();
        mConverView.setTag(this);
        mPosition = position;

    }

    /**
     * 可以理解成单例吧，获取一个viewholder
     * @param context
     * @param converView
     * @param layout
     * @param parent
     * @param position
     * @return
     */
    public static ViewHolder getViewHolder(Context context, View converView, int layout,
                                           ViewGroup parent, int position){
        if (converView ==null){
            //注意这里，其实跟baseadapter的判断是一致的，其中settag也是在viewholder里
            return  new ViewHolder(context,layout,parent,position);
        }else{
            ViewHolder holder = (ViewHolder) converView.getTag();
            mPosition = position;
            return holder;
        }
    }

    /**
     * 返回一个viewholder
     * @return
     */
    public View getConverView() {
        return mConverView;
    }

    /**
     * key-value 通过sparseArray来获取view，属性不同，这里用一个泛型来实现
     * @param viewid
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewid){
        View view = mViewSparseArray.get(viewid);
        if (view == null){
            view = mConverView.findViewById(viewid);
            mViewSparseArray.put(viewid,view);
        }
        return (T) view;
    }

    /**
     * 设置text
     * @param viewid
     * @param msg
     */
    public ViewHolder setText(int viewid, String msg){
        TextView tv = getView(viewid);
        tv.setVisibility(View.VISIBLE);
        tv.setText(msg);
        return this;
    }

    /**
     * 设置drawable
     * @param viewid
     * @param drawable
     */
    public ViewHolder setDrawable(int viewid, Drawable drawable){
        ImageView iv = getView(viewid);
        iv.setVisibility(View.VISIBLE);
        iv.setImageDrawable(drawable);
         return this;
    }

    /**
     * 设置drawable
     * @param viewid
     */
    public ViewHolder setDrawableVisibale(int viewid, int visiable){
        ImageView iv = getView(viewid);
        iv.setVisibility(visiable);
        return this;
    }


    /**
     * 设置drawable
     * @param viewid
     * @param resid
     */
    public ViewHolder setDrawable(int viewid, int resid){
        ImageView iv = getView(viewid);
        iv.setImageResource(resid);
        return this;
    }

    /**
     * 设置checkbox
     * @param viewid
     * @param status
     */
    public ViewHolder setCheckbox(int viewid, boolean status){
        CheckBox cb = getView(viewid);
        cb.setVisibility(View.VISIBLE);
        cb.setChecked(status);
        return this;

    }

    public ViewHolder setItemClickListener(int viewid, View.OnClickListener listener){
        View view = getView(viewid);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置itemview 的背景
     * @param viewid
     */
    public ViewHolder setItemBackground(int viewid, int resourid){
        View view = getView(viewid);
        //Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),resourid);
        view.setBackgroundResource(resourid);
        return this;
    }


}