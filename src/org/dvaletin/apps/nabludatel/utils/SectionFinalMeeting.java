package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.R;
import org.dvaletin.apps.nabludatel.ViolationCheckListActivity;

public class SectionFinalMeeting extends SectionList {
	public SectionFinalMeeting() {
		listItems = new ListViewActivityItem[] {
				new ListViewActivityItem("Жалобы",
						ViolationCheckListActivity.class,
						R.layout.section_final_meeting_complains),
				new ListViewActivityItem("Итоговое заседание",
						ViolationCheckListActivity.class,
						R.layout.section_final_meeting_meeting),
				new ListViewActivityItem("Наблюдатели на заседании",
						ViolationCheckListActivity.class,
						R.layout.section_final_meeting_observers),
				new ListViewActivityItem("Упаковки бюллетеней",
						ViolationCheckListActivity.class,
						R.layout.section_final_meeting_ballots),
				new ListViewActivityItem("Протокол и увеличенная форма",
						ViolationCheckListActivity.class,
						R.layout.section_final_meeting_protocol),
				new ListViewActivityItem("Копия протокола",
						ViolationCheckListActivity.class,
						R.layout.section_final_meeting_protocol_copy),
		};
	};
}
