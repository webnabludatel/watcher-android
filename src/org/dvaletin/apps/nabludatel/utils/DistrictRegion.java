package org.dvaletin.apps.nabludatel.utils;

public class DistrictRegion {
	public final static DistrictRegion[] RU_REGIONS = {
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

	private final int id;
	private final String title;

	public DistrictRegion(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public static DistrictRegion[] getRegions(){
		return RU_REGIONS;
		
	}
}
