package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.ABSNabludatelActivity;

public class ListViewActivityItem {
	final Class<? extends ABSNabludatelActivity> CLASS;
	final int LAYOUT;
	final String TITLE;
	int violationsCount = 0;
	
	ListViewActivityItem(final String title, final Class<? extends ABSNabludatelActivity> classId, final int layoutId){
		TITLE = title;
		CLASS = classId;
		LAYOUT = layoutId;
		updateViolations();
	}
	public String getTitle(){
		return TITLE;
	}
	
	public int getLayout(){
		return LAYOUT;
	}
	
	public Class<? extends ABSNabludatelActivity> getActivity(){
		return CLASS;
	}
	
	public String getViolationsDescription(){
		return "Отметок нет";
	}
	
	public int getViolationsCount(){
		return violationsCount;
	}
	
	public void updateViolations(){
		violationsCount = 0;
	}
}
