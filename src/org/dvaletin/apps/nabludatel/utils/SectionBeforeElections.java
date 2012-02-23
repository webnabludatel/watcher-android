package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.R;
import org.dvaletin.apps.nabludatel.ViolationCheckListActivity;

public class SectionBeforeElections extends SectionList {
	public SectionBeforeElections() {
		listItems = new ListViewActivityItem[] {
				new ListViewActivityItem("Избирательные ящики",
						ViolationCheckListActivity.class,
						R.layout.section_before_elections_bullot_box),
				new ListViewActivityItem("Списки избирателей",
						ViolationCheckListActivity.class,
						R.layout.section_before_elections_voters_lists),
				new ListViewActivityItem("Статистика участка",
						ViolationCheckListActivity.class,
						R.layout.section_before_elections_voters_count),
				new ListViewActivityItem("Бюллетени",
						ViolationCheckListActivity.class,
						R.layout.section_before_elections_ballots),
				new ListViewActivityItem("Наблюдатели на участке",
						ViolationCheckListActivity.class,
						R.layout.section_before_elections_observers) };
	};
}
