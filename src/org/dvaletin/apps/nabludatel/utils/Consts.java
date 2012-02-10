package org.dvaletin.apps.nabludatel.utils;

import java.io.File;

public class Consts {
	private static final String S3_ACCESS_KEY_ID = "AKIAJ4IVZVGUAAYUUWZQ";
	private static final String S3_SECRET_KEY = "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";
	
	private static final String S3_PICTURE_BUCKET = "nabludatel";
	

	public static final int MEDIA_TYPE_IMAGE = 0;
	public static final int MEDIA_TYPE_VIDEO = 1;
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE=100;
	public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE=101;
	public static final String section_elections_district_prefs = "section_elections_district";
	
	
	// Activity result codes
	public static final int ACTIVITY_RESULT_OK = 100;
	public static final int ACTIVITY_RESULT_NOT_OK = 101;
	public static final int ACTIVITY_RESULT_DATA_CHANGED = 102;
	
	
	//
	public static final String ACTIVITY_JSON_DATA = "json";
	
	public static final String getS3AccessKey(){
		return S3_SECRET_KEY;
	}
	
	public static final String getS3AccessKeyId(){
		return S3_ACCESS_KEY_ID;
	}
	public static final String getS3PictureBucket(){
		return S3_PICTURE_BUCKET;
	}
	
	public static final String [] ROOT_MENU_ITEMS = {
		"УИК",
        "Подготовка",
        "Ход голосования",
        "Подсчет голосов",
        "Заседание и протокол",
        "ТИК/ИКМО"
	};
	
	public static final String [] ROOT_MENU_DESCRIPTIONS = {
		"Не заполнено",
        "Не заполнено",
        "Не заполнено",
        "Не заполнено",
        "Не заполнено",
        "Не заполнено"
	};
	
	public static final String [] SECTION_ELECTIONS_DISTRICT = {
		"Избирательный участок"
	};
	
	public static final String [] SECTION_BEFORE_ELECTIONS = {
	    "Допуск на участок до 8:00",
	    "Урны",
	    "КОИБы",
	    "Оформление участка",
	    "Списки избирателей",
	    "Число избирателей"
	};
	
	
	public static String getDescription(int id){
		if(id == 0) return "Не заполнено";
		if(id % 10 == 1) return "Заполнен "+id+" пункт";
		if(id % 10 >1 && id % 10 < 5) return "Заполнено "+id+" пункта";
		return "Заполнено "+id+" пунктов";
	}
	
	public static String getAmazonS3Url(String deviceId, File file){
		return "https://s3-eu-west-1.amazonaws.com/"+getS3PictureBucket()+"/android/"+deviceId+"/"+file.getName();
	}
}
