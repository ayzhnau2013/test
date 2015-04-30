package com.mine.app.utils;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {

	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view,int id){
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if(viewHolder == null){
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		
		View childview = viewHolder.get(id);
		if(childview == null){
			childview = view.findViewById(id);
			viewHolder.put(id, childview);
		}
		return (T) childview;
	}
}
