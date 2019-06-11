package com.weidingqiang.skyworthui.tianjin.mainsoucesetting;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.weidingqiang.skyworthui.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/23.
 */

public class TJSourceSettingActivity extends Activity {
    private static final String TAG = "TJSourceSettingActivity";
	private ArrayList<String> mWindowTitles = new ArrayList<String>();
	private Context mContext;
	private int maxLength = 0;

	public static Intent newInstance(Context context) {
		Intent intent = new Intent(context, TJSourceSettingActivity.class);
		return intent;
	}

	/**
	 * const
	 */
	public static final int[] SOURCE_NORMAL = {
          R.drawable.android_normal,R.drawable.atv_focus,R.drawable.ypbpr_focus,R.drawable.dp_focus,
          R.drawable.dtv_focus,R.drawable.dvi_focus,R.drawable.hdmi_focus,
          R.drawable.ops_focus,R.drawable.usb_focus, R.drawable.vga_focus,
          R.drawable.ypbpr_focus};
	
	public static final String[] SOURCE_NAME = {"ANDROID","ATV","AV","DP","DTV","DVI","HDMI",
        "OPS","STORAGE","VGA","YPBPR"};
	private Map<String, Integer> mSourceFocusMap = new LinkedHashMap<>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.ist_source_setting_layout_tj);
        Window dialogwindow = getWindow();
        WindowManager.LayoutParams params = dialogwindow.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = 1000;
        params.height = 600;
        dialogwindow.setAttributes(params);
	    mContext = this;
//		registerReceiverInit();
        initData();
    }

	//end by zhudj,20180504
	/*public void registerReceiverInit(){
		IntentFilter filter = new IntentFilter();
		filter.addAction(IstEventManager.ACTION_WINDOW_CHANGED);
		filter.addAction("action.ist.close.sourceactivity");
		mContext.registerReceiver(mReceiver, filter);
	}

	BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals(IstEventManager.ACTION_WINDOW_CHANGED)){
				mWindowTitles.clear();
				mWindowTitles = intent.getStringArrayListExtra(IstEventManager.ACTION_GET_ATTRS);
			}
			if("action.ist.close.sourceactivity".equals(action)){
				finish();
			}
		}
	};*/

	private void sendKeyCode(){
		final Instrumentation inst = new Instrumentation();
		new Thread(new Runnable() { 			
			public void run() {
				Instrumentation ins = new Instrumentation();
				ins.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK); 			
			}
		}).start();
	}
	private boolean isWindowShow(String title){
		if (mWindowTitles != null) {
			for (String etitle : mWindowTitles) {
				if (etitle.equals(title)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkStringValid(String str){
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if(m.find()){
			if(str.length() > 6){
				maxLength = 6;
				return false;
			}else{
				return true;
			}
		}else{
			if(str.length() > 10){
				maxLength = 10;
				return false;
			}else{
				return true;
			}
		}
	}
	
/*	@Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}*/
	//end zhudj

	public static final int[] CN_NORMAL = {
			R.drawable.tj_android_normal,R.drawable.tj_ops_focus,R.drawable.tj_hdmi_focus,R.drawable.tj_hdmi_focus,R.drawable.tj_hdmi_focus,
			R.drawable.tj_vga_focus,R.drawable.tj_ypbpr_focus,R.drawable.tj_ypbpr_focus,
			R.drawable.tj_atv_focus,R.drawable.tj_dtv_focus};
	public static final String[] CN_NAME = {"安卓","OPS","前置HDMI","HDMI1","HDMI2","VGA1","AV",
			"分量","模拟电视","数字电视"};
    private void initData() {
    	for (int i = 0; i < SOURCE_NORMAL.length; i++) {
            mSourceFocusMap.put(SOURCE_NAME[i], SOURCE_NORMAL[i]);
        }
    	
    	findViewById(R.id.source_title_back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});


		Set<Map<String,Object>> maps = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("drawableId",CN_NORMAL[i]);
			map.put("value",CN_NAME[i]);
			maps.add(map);
		}

		final List<SourceBean> sourceBeanList = new ArrayList<>();
		for (Map<String, Object> enMap : maps) {
			SourceBean bean = new SourceBean();
//			bean.setKey(enMap.getKey());
			bean.setIconid((int) enMap.get("drawableId"));
			bean.setSource((String) enMap.get("value"));
			sourceBeanList.add(bean);

			//   Log.d(TAG, "initData: "+enMap.getKey()+" --- "+enMap.getValue());
		}

		/*Map<String,String> map = IstEventManager.getInstance().getInputSrcMap();
        Set<Map.Entry<String,String>> maps = map.entrySet();
        final List<SourceBean> sourceBeanList = new ArrayList<>();
        for (Map.Entry<String, String> enMap : maps) {
            SourceBean bean = new SourceBean();
            bean.setKey(enMap.getKey());
            bean.setIconid(getDrawableId(bean.getKey()));
            bean.setSource(enMap.getValue());
            sourceBeanList.add(bean);

         //   Log.d(TAG, "initData: "+enMap.getKey()+" --- "+enMap.getValue());
        }*/


        final CommonAdapter<SourceBean> adapter = new CommonAdapter<SourceBean>(TJSourceSettingActivity.this,
                sourceBeanList,R.layout.ist_source_setting_gridview_item_tj) {
            @Override
            public void convert(ViewHolder viewHolder, SourceBean data, int position) {
                viewHolder.setText(R.id.source_key,data.getSource());//
                if(data.getIconid() != -1){
                	viewHolder.setDrawable(R.id.source_icon, data.getIconid());
                }
                       // .setText(R.id.source_value,data.getSource());
            }
        };

        GridView gridView = (GridView) findViewById(R.id.source_setting_grid);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            
//                final SourceBean bean = sourceBeanList.get(position);
//                final CustomPopupWindow popupWindow = new CustomPopupWindow.Builder()
//                        .setContext(TJSourceSettingActivity.this)
//                        .setContentView(R.layout.ist_source_change_name_pop)
//                        .setwidth(620)
//                        .setheight(220)
//                        .setFouse(true)
//                        .setOutSideCancel(true)
//                        .setBackGroudAlpha(TJSourceSettingActivity.this,0.5f)
//                        .builder()
//                        .showAtLocation(R.layout.ist_source_setting_layout,Gravity.CENTER,0,0);
//
//                popupWindow.setText(R.id.current_source,
//                        bean.getSource());
//                final EditText editText = (EditText) popupWindow.getItemView(R.id.source_edit);
//                editText.setHint(bean.getKey());
//                popupWindow.setOnClickListener(R.id.pop_exit_sure, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String editInputString = editText.getText().toString();
//						if(editInputString.length() == 0){
//							if(isWindowShow("InputMethod")){
//								sendKeyCode();
//							}
//							Toast.makeText(mContext,
//								mContext.getResources().getString(R.string.ist_channel_name_null_tip), 3000).show();
//							return;
//						}
//						if(isSourceNamerepeat(editInputString)){
//							Toast.makeText(mContext, mContext.getString(R.string.same_source_name), Toast.LENGTH_SHORT).show();
//							return;
//						}
//						checkStringValid(editInputString);
//                        if (checkStringValid(editInputString)) {
//							IstEventManager.getInstance().
//                            setInputSrcCustomerNameMap(bean.getKey(),editInputString);
//                        	int index = sourceBeanList.indexOf(bean);
//                            sourceBeanList.remove(bean);
//                            bean.setSource(editInputString);
//                            sourceBeanList.add(index,bean);
//                            adapter.notifyDataSetChanged();
//                    		popupWindow.dismiss();
//						}else{
//							if(isWindowShow("InputMethod")){
//								sendKeyCode();
//							}
//							Toast.makeText(mContext,
//								String.format(mContext.getResources().getString((R.string.ist_channel_name_tip)),maxLength), 3000).show();
//						}
//
//                    }
//                });
//                popupWindow.setOnClickListener(R.id.pop_exit_cancel, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                });
            }
        });
    }
    
//    private boolean isSourceNamerepeat(String name) {
//        Map<String,String> sourcemap = IstEventManager.getInstance().getInputSrcMap();
//        for (Map.Entry<String, String> map : sourcemap.entrySet()) {
//            if (map.getValue().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }
    
   private int getDrawableId(String key){
	   Set<Map.Entry<String, Integer>> entries = mSourceFocusMap.entrySet();
       for (Map.Entry<String, Integer> entry : entries) {
           if (key.contains(entry.getKey())){
               
               return entry.getValue();
           }
       }
      return -1;
   }
}
