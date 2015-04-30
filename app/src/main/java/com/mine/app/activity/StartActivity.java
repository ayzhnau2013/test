package com.mine.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mine.app.R;
import com.mine.app.adapter.PagerBaseAdapter;
import com.mine.app.fragment.FourthFragment;
import com.mine.app.fragment.MineFragment;
import com.mine.app.fragment.PlayFragment;
import com.mine.app.fragment.SecondFragment;
import com.mine.app.fragment.ThirdFragment;
import com.mine.app.utils.LogUtils;
import com.mine.app.widget.LineIndicator;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener{
	private String TAG = "StartActivity";
    private FragmentManager mFragmentManager;
	private ViewPager mPager;
	private RadioGroup mGroup;
	private LineIndicator mIndicator;
	private RadioButton[] mButtons;
	
	private PagerBaseAdapter mAdapter;
	private List<Fragment> mFragments;
	private int mCurrIndex;

    private PlayFragment mPlayFragment;
	@Override
	protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.main_layout);
        initView();
        addListener();
        initData();
        initAdapter();
	}

	private void initAdapter() {
		mFragments = new ArrayList<Fragment>();
		mFragments.add(new MineFragment());
		mFragments.add(new SecondFragment());
		mFragments.add(new ThirdFragment());
		mFragments.add(new FourthFragment());
		mAdapter = new PagerBaseAdapter(getSupportFragmentManager(),mFragments);
		mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(this);
	}

	private void initData() {
		mCurrIndex = 0;
		mButtons[mCurrIndex].setTextColor(getResources().getColor(R.color.cpb_blue));

        mFragmentManager = getSupportFragmentManager();
        mPlayFragment = (PlayFragment) mFragmentManager.findFragmentById(R.id.main_play_fragment);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_play_fragment,mPlayFragment);
        transaction.commit();
	}

	private void addListener() {
		mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int index = getButtonIndex(checkedId);
				
				if(index != mCurrIndex){
					mPager.setCurrentItem(index);
				}
			}
		});
	}

	protected int getButtonIndex(int checkedId) {
		switch(checkedId){
		case R.id.indicator_one:
			return 0;
		case R.id.indicator_two:
			return 1;
		case R.id.indicator_three:
			return 2;
		case R.id.indicator_four:
			return 3;
		}
		return -1;
	}

	private void initView() {
		mPager = (ViewPager) findViewById(R.id.main_viewpager);
		mGroup = (RadioGroup) findViewById(R.id.indicator_group);
		mIndicator = (LineIndicator) findViewById(R.id.title_indicator);
		
		mButtons = new RadioButton[4];
		mButtons[0] = (RadioButton) findViewById(R.id.indicator_one);
		mButtons[1] = (RadioButton) findViewById(R.id.indicator_two);
		mButtons[2] = (RadioButton) findViewById(R.id.indicator_three);
		mButtons[3] = (RadioButton) findViewById(R.id.indicator_four);
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		LogUtils.i(TAG,"onPageScrollStateChanged:--->"+arg0+"<----");
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		LogUtils.i(TAG, "onPageScrolled:("+arg0+","+arg1 + "," +arg2 +")");
	}

	@Override
	public void onPageSelected(int arg0) {
		LogUtils.i(TAG, "onPageSelected:("+arg0+")");
		if(mCurrIndex != arg0){
			mButtons[arg0].setTextColor(getResources().getColor(R.color.cpb_blue));
			mButtons[mCurrIndex].setTextColor(getResources().getColor(R.color.category_color_title));
			mPager.setCurrentItem(arg0);
            mCurrIndex = arg0;
		}
	}

    @Override
	public void onClick(View v) {
		int index = getButtonIndex(v.getId());
		
		if(index != mCurrIndex){
			mPager.setCurrentItem(index);
		}
	}

}
