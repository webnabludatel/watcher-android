package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.R;
import org.dvaletin.apps.nabludatel.ViolationCheckListActivity;

public class SectionCounting extends SectionList {
	public SectionCounting() {
		listItems = new ListViewActivityItem[] {
				new ListViewActivityItem("Неиспользованные бюллетени",
						ViolationCheckListActivity.class,
						R.layout.section_counting_unused_ballots),
				new ListViewActivityItem("Списки проголосовавших",
						ViolationCheckListActivity.class,
						R.layout.section_counting_confirmed_voters),
				new ListViewActivityItem("Переносные ящики",
						ViolationCheckListActivity.class,
						R.layout.section_counting_absentee_ballot),
				new ListViewActivityItem("Стационарные урны",
						ViolationCheckListActivity.class,
						R.layout.section_counting_ballot_box),
				new ListViewActivityItem("Сортировка и подсчёт (без КОИБов)",
						ViolationCheckListActivity.class,
						R.layout.section_counting_counting_ballots),
				new ListViewActivityItem("Участки с КОИБами",
						ViolationCheckListActivity.class,
						R.layout.section_counting_koib),
				new ListViewActivityItem("Контрольные соотношения",
						ViolationCheckListActivity.class,
						R.layout.section_counting_control_calculations),
		
//				new ListViewActivityItem("Этапы подсчётов",
//						ViolationCheckListActivity.class,
//						R.layout.section_counting_control_stages),
				
		};
	};
}
