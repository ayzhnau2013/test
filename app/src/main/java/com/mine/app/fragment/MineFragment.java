package com.mine.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.mine.app.R;
import com.mine.app.adapter.MeGridViewAdapter;
import com.mine.app.utils.LogUtils;

public class MineFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{

    private String TAG = "MineFragment";
	private View view;
    private GridView mGridView;
    private RelativeLayout mLoaclMusicLayout;
    private MeGridViewAdapter mLocalAdapter;

    private LayoutInflater mInflater;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mInflater = getActivity().getLayoutInflater();
		view = mInflater.inflate(R.layout.mine_fargment,null);
        mGridView = (GridView)view.findViewById(R.id.gridview);
        mLoaclMusicLayout = (RelativeLayout) view.findViewById(R.id.loacal_layout);

        mLocalAdapter = new MeGridViewAdapter(getActivity());
        mGridView.setAdapter(mLocalAdapter);

        mLoaclMusicLayout.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);
		return view;
	}
	
	@Override
	public int setId() {
		return R.layout.mine_fargment;
	}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loacal_layout:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogUtils.i(TAG,"i="+i+"...........l="+l);
    }
}
