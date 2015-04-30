package com.mine.app.fragment;

import android.support.v4.app.Fragment;

import com.mine.app.R;

public class FragmentFactory {

	public static Fragment getInstanceByIndex(int index) {  
        Fragment fragment = null;  
        switch (index) {  
            case R.id.indicator_one:  
                fragment = new MineFragment();  
                break;  
            case R.id.indicator_two:  
                fragment = new SecondFragment();  
                break;  
            case R.id.indicator_three:  
                fragment = new ThirdFragment();  
                break;  
            case R.id.indicator_four:  
                fragment = new FourthFragment();  
                break;  
        }  
        return fragment;  
    }  
}
