package org.dvaletin.apps.nabludatel.utils;

import java.util.ArrayList;

import org.dvaletin.apps.nabludatel.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class TabAdapter {
	ArrayList<Integer> tabs;

	private LayoutInflater mInflater;
	Context mContext;
	View mRootView;
	public TabAdapter(Context pContext, View rootView){
//		root = rootViewId;
		mRootView = rootView;
		mContext = pContext;
		mInflater = LayoutInflater.from(mContext);
		tabs = new ArrayList<Integer>();
	}
	
	public void addTab(int pTabLayoutId){
		tabs.add(pTabLayoutId);
	}
	
	public void switchTab(int tabId){
		if(tabId >= 0 && tabId < tabs.size()){
			mInflater = LayoutInflater.from(mContext);
			mRootView = mInflater.inflate(tabs.get(tabId),
					null);
		}
	}
}
