package com.mine.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.mine.app.R;
import com.mine.app.adapter.PagerBaseAdapter;
import com.mine.app.fragment.FourthFragment;
import com.mine.app.fragment.MineFragment;
import com.mine.app.fragment.PlayFragment;
import com.mine.app.fragment.SecondFragment;
import com.mine.app.fragment.ThirdFragment;
import com.mine.app.utils.LogUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AbandonStartActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener{
	private FragmentManager mFragmentManager;
	private ViewPager mPager;
	private RadioGroup mGroup;
	private ImageView mIndicator;
	private RadioButton[] mButtons;
	
	private PagerBaseAdapter mAdapter;
	private List<Fragment> mFragments;
	private int mScreenWidth;
	private int mCurrIndex;

    private PlayFragment mPlayFragment;
	@Override
	protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.main_layout);
        initView();
        addLisener();
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
		mPager.setOnPageChangeListener(this);
		mPager.setCurrentItem(mCurrIndex);
	}

	private void initData() {
		mCurrIndex = 0;
		mButtons[mCurrIndex].setTextColor(getResources().getColor(R.color.pink));
		
        LayoutParams cursor_Params = mIndicator.getLayoutParams();
        cursor_Params.width = getRealWidth();
        mIndicator.setLayoutParams(cursor_Params);

        mFragmentManager = getSupportFragmentManager();
        mPlayFragment = (PlayFragment) mFragmentManager.findFragmentById(R.id.main_play_fragment);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_play_fragment,mPlayFragment);
        transaction.commit();
	}

	private int getRealWidth() {
		mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
		return mScreenWidth /4 ;
	}

	private void addLisener() {
		mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int index = getButtonIndex(checkedId);
				
				if(index != mCurrIndex){
					mPager.setCurrentItem(index);
					setTransAnim(index);
				}
			}
		});
	}

	protected void setTransAnim(int index) {
		TranslateAnimation animation = new TranslateAnimation(mCurrIndex * mScreenWidth /4,index * mScreenWidth /4, 0f, 0f);
		mCurrIndex = index;
		animation.setInterpolator(new LinearInterpolator());
		animation.setFillAfter(true);
		animation.setDuration(100);
		mIndicator.startAnimation(animation);
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
		mIndicator = (ImageView) findViewById(R.id.title_indicator);
		
		mButtons = new RadioButton[4];
		mButtons[0] = (RadioButton) findViewById(R.id.indicator_one);
		mButtons[1] = (RadioButton) findViewById(R.id.indicator_two);
		mButtons[2] = (RadioButton) findViewById(R.id.indicator_three);
		mButtons[3] = (RadioButton) findViewById(R.id.indicator_four);
		
	}

    private int mScrollStatus;
	@Override
	public void onPageScrollStateChanged(int arg0) {
        mScrollStatus = arg0;
		//LogUtils.i("AbandonStartActivity","onPageScrollStateChanged:--->"+arg0+"<----");
	}

	float rote;
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//ogUtils.i("AbandonStartActivity", "onPageScrolled:("+arg0+","+arg1 + "," +arg2 +")" + "--->"+rote +"<---");
//		float startx = 0;
//		float endx = 0;
//		if(rote != arg1 && arg1 !=0 ){
//			startx = (arg0 + rote) * mScreenWidth /4;
//			endx = startx + mScreenWidth /4;
//		}
//        else if(rote != arg1 && arg1 == 0.0){
//			startx = (arg0 + rote) * mScreenWidth /4;
//			endx = arg0 *mScreenWidth/4;
//            rote = 0;
//		}
//
//		TranslateAnimation animation = new TranslateAnimation(startx,endx, 0f, 0f);
//		animation.setInterpolator(new LinearInterpolator());
//		animation.setFillAfter(true);
//		animation.setDuration(100);
//		mIndicator.startAnimation(animation);
//		rote = arg1;
	}

	@Override
	public void onPageSelected(int arg0) {
		//LogUtils.i("AbandonStartActivity", "onPageSelected:("+arg0+")");
		if(mCurrIndex != arg0 && mScrollStatus == ViewPager.SCROLL_STATE_IDLE){
            rote = 0;
			mButtons[arg0].setTextColor(getResources().getColor(R.color.pink));
			mButtons[mCurrIndex].setTextColor(getResources().getColor(R.color.category_color_title));
			mPager.setCurrentItem(arg0);
			setTransAnim(arg0);
		}
	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
	public void onClick(View v) {
		int index = getButtonIndex(v.getId());
		
		if(index != mCurrIndex){
			mPager.setCurrentItem(index);
			setTransAnim(index);
		}
	}

}
