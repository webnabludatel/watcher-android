package org.dvaletin.apps.nabludatel.utils;

public class Consts {
	public static final int MEDIA_TYPE_IMAGE = 0;
	public static final int MEDIA_TYPE_VIDEO = 1;
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 101;
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
			"Голосование", "Подсчет", "Заседание и протокол"};


	public static final String[] ROOT_MENU_DESCRIPTIONS = {
			"Отметок нет",  "Отметок нет", "Отметок нет", "Отметок нет"};

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
		"tik",
		"ikmo"
	};
	
	public static final String PHOTO_FILE = "photo";
	public static final String VIDEO_FILE = "video";
	public static final String PREFS_ELECTIONS_DISRICT = "elections_district";
	public static final String PREFS_TITLE = "window_title";
	public static final String PREFS_LAYOUT_ID = "layout_id";
	

	public static String getDescriptionFill(int id) {
		if (id == 0)
			return "Не заполнено";
		if (id % 10 == 1)
			return "Заполнен " + id + " пункт";
		if (id % 10 > 1 && id % 10 < 5)
			return "Заполнено " + id + " пункта";
		return "Заполнено " + id + " пунктов";
	}

	public static String getDescriptionViolation(int id) {
		if (id == 0)
			return "Нарушений не зафиксировано";
		if (id == 1)
			return "Зафиксировано " + id + " нарушение";
		if (id > 1 && id < 5)
			return "Зафиксировано " + id + " нарушения";
		return "Зафиксировано " + id + " нарушений";
	}
}
