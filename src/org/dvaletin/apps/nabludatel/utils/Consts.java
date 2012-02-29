package org.dvaletin.apps.nabludatel.utils;

public class Consts {
	public static final int MEDIA_TYPE_IMAGE = 0;
	public static final int MEDIA_TYPE_VIDEO = 1;
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 101;
	public static final int GALLERY_IMAGE_ACTIVITY_REQUEST_CODE = 102;
	public static final int GALLERY_VIDEO_ACTIVITY_REQUEST_CODE = 103;
	public static final String section_elections_district_prefs = "section_elections_district";

	// Activity result codes
	public static final int ACTIVITY_RESULT_OK = 100;
	public static final int ACTIVITY_RESULT_NOT_OK = 101;
	public static final int ACTIVITY_RESULT_DATA_CHANGED = 102;
	public static final int ACTIVITY_RESULT_NEW_ELECTIONS_DISTRICT = 103;
	

	//
	public static final String ACTIVITY_JSON_DATA = "json";
	public static final String ACTIVITY_URL_DATA = "url";

	public static final int SEEKBAR_FALSE = 0;
	public static final int SEEKBAR_TRUE = 2;


	public static final String[] ROOT_MENU_ITEMS = {"Открытие участка",
			"Голосование", "Подсчет", "Заседание и протокол", "ТИК/ИКМО"};


	public static final String[] ROOT_MENU_DESCRIPTIONS = {
			"Отметок нет",  "Отметок нет", "Отметок нет", "Отметок нет", "Отметок нет",};

	public static final String[] SECTION_ELECTIONS_DISTRICT = { "Избирательный участок" };

	public static final String[] SECTION_ELECTIONS_DISTRICT_DESCRIPTIONS = { "Не заполнено" };

	public static final String[] SECTION_BEFORE_ELECTIONS = {
			"Избирательные ящики", "Списки избирателей", "Статистика участка", "Бюллетени",
			"Наблюдатели на участке" };

	public static final String[] SECTION_BEFORE_ELECTIONS_DESCRIPTIONS = {
		"Отметок нет",  "Отметок нет", "Отметок нет", "Отметок нет", "Отметок нет"};

	public static final String[] SECTION_DURING_ELECTIONS = { "Данные о явке",
			"Помехи наблюдателям", "Давление на голосующих",
			"Подозрительные голосующие",
			"Голосование пачкой бюллетеней (вброс)",
			"Нарушения при голосовании вне участка", };

	public static final String[] SECTION_DURING_ELECTIONS_DESCRIPTIONS = {
			"Нарушений не зафиксировано", "Нарушений не зафиксировано",
			"Нарушений не зафиксировано", "Нарушений не зафиксировано",
			"Нарушений не зафиксировано", "Нарушений не зафиксировано" };
	
	public static final String[] SECTION_COUNTING = {
		"Неиспользованные бюллетени", 
		"Проголосовавшие избиратели",
		"Вскрытие стационарных урн",
		"Вскрытие переносных ящиков",
		"Сортировка и подсчет",
		"Контрольные соотношения"
	};
	
	public static final String[] SECTION_COUNTING_DESCRIPTIONS = {
		"Нарушений не зафиксировано", 
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано"
	};
	
	public static final String [] SECTION_FINAL_MEETING = {
		"Итоговое заседание",
		"Упаковки бюллетеней",
		"Протокол и увеличенная копия"
	};
	
	public static final String [] SECTION_FINAL_MEETING_DESCRIPTIONS = {
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано"	
	};
	
	public static final String [] SECTION_TIK_IKMO = {
		"Помещение ТИК",
		"Прием протокола и занесение данных в УФСТ",
		"Жалобы"
	};
	
	public static final String [] SECTION_TIK_IKMO_DESCRIPTION = {
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано",
		"Нарушений не зафиксировано"
	};
	
	public static final String [] DISTRICT_TYPE = {
		"uik",
		"tik"
	};
	
	public static final String PHOTO_FILE = "photo";
	public static final String VIDEO_FILE = "video";
	public static final String PREFS_ELECTIONS_DISRICT = "elections_district";
	public static final String PREFS_TITLE = "window_title";
	public static final String PREFS_LAYOUT_ID = "layout_id";
	public static final String PREFS_VIOLATIONS = "violations_count";
	public static final String PREFS_LATITUDE = "lat";
	public static final String PREFS_LONGITUDE = "lng";
	public static final String PREFS_FILENAME = "org.dvaletin.apps.global.prefs";
	public static final String PREFS_LAST_TAB = "last_bar";
	public static final String PREFS_FACEBOOK_ACCESS_TOKEN = "facebook_access_token";
	public static final String PREFS_FACEBOOK_ACCESS_EXPIRES = "facebook_access_expires";
	public static final String PREFS_TWITTER_SECRET = "twitter_secret";
	public static final String PREFS_TWITTER_TOKEN = "twitter_token";
	public static final String[] TIK_IKMO_MENU_ITEMS = {
		"ТИК/ИКМО"
	};
	public static final String[] TIK_IKMO_MENU_DESCRIPTIONS = {
		"Отметок нет",
	};

	public static final int VIOLATION_NOTIFICATION_ID = 1;
	public static final int MEDIA_NOTIFICATION_ID = 2;
	public static final String PREFS_FACEBOOK_EMAIL = "facebook_email";
	public static final String PREFS_USER_ID = "user_id";

	public static String getViolationDescription(int id) {
		if (id == 0)
			return "Отметок нет";
		if (id == 1)
			return "Отмечен " + id + " пункт";
		if (id > 1 && id < 5)
			return "Отмечено " + id + " пункта";
		return "Отмечено " + id + " пунктов";
	}
	
	public static String getGoodText(int id){
		if(id == 0)
			return "Нет отметок";
		if(id == 1)
			return "Выполнено "+id+" требование";
		if(id > 1 && id < 5)
			return "Выполнено " +id+ " требования";
		if(id >= 5 && id < 21 )
			return "Выполнено " +id+ " требований";
		if(id % 10 == 1)
			return "Выполнено " +id+ " требование";
		if(id % 10 > 1 && id % 10 < 5){
			return "Выполнено " +id+ " требования";
		} else return "Выполнено " +id+ "требований"; 
	}
	
	public static String getBadText(int id){
		if(id == 0)
			return "нет нарушений";
		if(id == 1)
			return ""+id+" нарушение";
		if(id > 1 && id < 5)
			return "" +id+ " нарушения";
		if(id >= 5 && id < 21 )
			return "" +id+ " нарушений";
		if(id % 10 == 1)
			return "" +id+ " нарушение";
		if(id % 10 > 1 && id % 10 < 5){
			return "" +id+ " нарушения";
		}else return ""+id+" нарушений";
	}
}
