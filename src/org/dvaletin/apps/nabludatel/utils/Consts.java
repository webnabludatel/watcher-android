package org.dvaletin.apps.nabludatel.utils;

public class Consts {
	public static final int MEDIA_TYPE_IMAGE = 0;
	public static final int MEDIA_TYPE_VIDEO = 1;
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 101;
	public static final int GALLERY_IMAGE_ACTIVITY_REQUEST_CODE = 102;
	public static final int GALLERY_VIDEO_ACTIVITY_REQUEST_CODE = 103;
	public static final int DISTRICT_ACTIVITY_REQUEST_CODE      = 104;
	

	//
	public static final String ACTIVITY_URL_DATA = "url";

	public static final String[] ROOT_MENU_ITEMS = {"Открытие участка",
			"Голосование", "Подсчёт", "Заседание и протокол"};


	public static final String[] ROOT_MENU_DESCRIPTIONS = {
			"Отметок нет",  "Отметок нет", "Отметок нет", "Отметок нет", "Отметок нет",};

	public final static DistrictRegion[] REGIONS = {
			new DistrictRegion(-1, "Выберите регион"),
			new DistrictRegion(77, "Город Москва"),
			new DistrictRegion(78, "Город Санкт-Петербург"),
			new DistrictRegion(1, "Республика Адыгея"),
			new DistrictRegion(2, "Республика Башкортостан"),
			new DistrictRegion(3, "Республика Бурятия"),
			new DistrictRegion(4, "Республика Алтай"),
			new DistrictRegion(5, "Республика Дагестан"),
			new DistrictRegion(6, "Республика Ингушетия"),
			new DistrictRegion(7, "Кабардино-Балкарская Республика"),
			new DistrictRegion(8, "Республика Калмыкия"),
			new DistrictRegion(9, "Карачаево-Черкесская Республика"),
			new DistrictRegion(10, "Республика Карелия"),
			new DistrictRegion(11, "Республика Коми"),
			new DistrictRegion(12, "Республика Марий Эл"),
			new DistrictRegion(13, "Республика Мордовия"),
			new DistrictRegion(14, "Республика Саха (Якутия)"),
			new DistrictRegion(15, "Республика Северная Осетия - Алания"),
			new DistrictRegion(16, "Республика Татарстан (Татарстан)"),
			new DistrictRegion(17, "Республика Тыва"),
			new DistrictRegion(18, "Удмуртская Республика"),
			new DistrictRegion(19, "Республика Хакасия"),
			new DistrictRegion(20, "Чеченская Республика"),
			new DistrictRegion(21, "Чувашская Республика - Чувашия"),
			new DistrictRegion(22, "Алтайский край"),
			new DistrictRegion(23, "Краснодарский край"),
			new DistrictRegion(24, "Красноярский край"),
			new DistrictRegion(25, "Приморский край"),
			new DistrictRegion(26, "Ставропольский край"),
			new DistrictRegion(27, "Хабаровский край"),
			new DistrictRegion(28, "Амурская область"),
			new DistrictRegion(29, "Архангельская область"),
			new DistrictRegion(30, "Астраханская область"),
			new DistrictRegion(31, "Белгородская область"),
			new DistrictRegion(32, "Брянская область"),
			new DistrictRegion(33, "Владимирская область"),
			new DistrictRegion(34, "Волгоградская область"),
			new DistrictRegion(35, "Вологодская область"),
			new DistrictRegion(36, "Воронежская область"),
			new DistrictRegion(37, "Ивановская область"),
			new DistrictRegion(38, "Иркутская область"),
			new DistrictRegion(39, "Калининградская область"),
			new DistrictRegion(40, "Калужская область"),
			new DistrictRegion(41, "Камчатский край"),
			new DistrictRegion(42, "Кемеровская область"),
			new DistrictRegion(43, "Кировская область"),
			new DistrictRegion(44, "Костромская область"),
			new DistrictRegion(45, "Курганская область"),
			new DistrictRegion(46, "Курская область"),
			new DistrictRegion(47, "Ленинградская область"),
			new DistrictRegion(48, "Липецкая область"),
			new DistrictRegion(49, "Магаданская область"),
			new DistrictRegion(50, "Московская область"),
			new DistrictRegion(51, "Мурманская область"),
			new DistrictRegion(52, "Нижегородская область"),
			new DistrictRegion(53, "Новгородская область"),
			new DistrictRegion(54, "Новосибирская область"),
			new DistrictRegion(55, "Омская область"),
			new DistrictRegion(56, "Оренбургская область"),
			new DistrictRegion(57, "Орловская область"),
			new DistrictRegion(58, "Пензенская область"),
			new DistrictRegion(59, "Пермская область"),
			new DistrictRegion(60, "Псковская область"),
			new DistrictRegion(61, "Ростовская область"),
			new DistrictRegion(62, "Рязанская область"),
			new DistrictRegion(63, "Самарская область"),
			new DistrictRegion(64, "Саратовская область"),
			new DistrictRegion(65, "Сахалинская область"),
			new DistrictRegion(66, "Свердловская область"),
			new DistrictRegion(67, "Смоленская область"),
			new DistrictRegion(68, "Тамбовская область"),
			new DistrictRegion(69, "Тверская область"),
			new DistrictRegion(70, "Томская область"),
			new DistrictRegion(71, "Тульская область"),
			new DistrictRegion(72, "Тюменская область"),
			new DistrictRegion(73, "Ульяновская область"),
			new DistrictRegion(74, "Челябинская область"),
			new DistrictRegion(75, "Забайкальский край"),
			new DistrictRegion(76, "Ярославская область"),
			new DistrictRegion(79, "Еврейская автономная область"),
			new DistrictRegion(80, "Агинский Бурятский АО"),
			new DistrictRegion(81, "Коми-Пермятский АО"),
			new DistrictRegion(82, "Корякский АО"),
			new DistrictRegion(83, "Ненецкий АО"),
			new DistrictRegion(84, "Таймырский (Долгано-Ненецкий) АО"),
			new DistrictRegion(85, "Усть-Ордынский Бурятский АО"),
			new DistrictRegion(86, "Ханты-Мансийский автономный округ - Югра"),
			new DistrictRegion(87, "Чукотский автономный округ"),
			new DistrictRegion(88, "Эвенкийский автономный округ"),
			new DistrictRegion(89, "Ямало-Ненецкий автономный округ"),
	};

	public static final String [] POLLING_PLACE_TYPE = {
		"uik",
		"tik"
	};

	public static final String PHOTO_FILE = "photo";
	public static final String VIDEO_FILE = "video";
	public static final String PREFS_CURRENT_POLLING_PLACE_ID = "current_polling_place_id";
	public static final String PREFS_TITLE = "window_title";
	public static final String PREFS_LAYOUT_ID = "layout_id";
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

	public static final int DATA_NOTIFICATION_ID = 1;
	public static final int MEDIA_NOTIFICATION_ID = 2;
	public static final String PREFS_FACEBOOK_EMAIL = "facebook_email";
	public static final String PREFS_USER_ID = "user_id";
	public static final String PREFS_USER_EMAIL = "user_email";
	public static final String PREFS_DEVICE_ID = "device_id";

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
