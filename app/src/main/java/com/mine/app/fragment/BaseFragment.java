package com.mine.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	private LayoutInflater mInflater;
	private int mLayoutId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater = getActivity().getLayoutInflater();
		mLayoutId = setId();
		return mInflater.inflate(mLayoutId, null);
	}
	
	public abstract int setId();
}
