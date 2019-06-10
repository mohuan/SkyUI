package com.weidingqiang.skyworthui.old.mainsoucesetting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    private int mLayoutId;
    public CommonAdapter(Context context, List<T> datas, int layoutid) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutid;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public abstract void convert(ViewHolder viewHolder,T data,int position);

    /**
     * 这里把布局什么的，都在getview中，设置好，其中viewholder是我们的通用的adapter
     * 然后把需要实现的逻辑用抽象方法公布出去，自己去实现
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(mContext,view,mLayoutId,viewGroup,i);
        convert(viewHolder,getItem(i),i);
        return viewHolder.getConverView();
    };

}