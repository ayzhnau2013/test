package com.mine.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mine.app.R;
import com.mine.app.utils.ViewHolder;

/**
 * Created by zhu on 15-4-29.
 */
public class MeGridViewAdapter extends BaseAdapter{

    public static final int LOACL_AMOUNT = 4;
    private Context mCtx;
    private ImageView iv;
    private TextView tv;
    private int[] images = {R.drawable.img_apshare_song,R.drawable.img_apshare_song,
            R.drawable.img_apshare_song,R.drawable.img_apshare_song};
    private String[] titles = {"我的最爱","我的下载","我的歌单","最近播放"};
    public MeGridViewAdapter(Context ctx){
        mCtx = ctx;

    }
    @Override
    public int getCount() {
        return LOACL_AMOUNT;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        String local_show = (String) getItem(position);
        int img_show = images[position];
        if(convertView == null){
            convertView = View.inflate(mCtx, R.layout.main_gridview_item,null);
        }
        iv = ViewHolder.get(convertView,R.id.gridview_item_iv);
        tv = ViewHolder.get(convertView,R.id.gridview_item_name);
        iv.setImageResource(img_show);
        tv.setText(local_show);
        return convertView;
    }

}
