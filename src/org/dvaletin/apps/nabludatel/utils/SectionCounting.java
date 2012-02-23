package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.R;
import org.dvaletin.apps.nabludatel.ViolationCheckListActivity;

public class SectionCountingElections extends SectionList {
	public SectionCountingElections() {
		listItems = new ListViewActivityItem[] {
				new ListViewActivityItem("Состояние участка",
						ViolationCheckListActivity.class,
						R.layout.section_during_elections_appearance),
				new ListViewActivityItem("Условия наблюдения",
						ViolationCheckListActivity.class,
						R.layout.section_during_elections_ballot),
				new ListViewActivityItem("Давление на голосующих",
						ViolationCheckListActivity.class,
						R.layout.section_during_elections_ballot_process_pressure),
				new ListViewActivityItem("Подозрительные голосующие",
						ViolationCheckListActivity.class,
						R.layout.section_during_elections_suspicious_voters),
				new ListViewActivityItem("Вброс",
						ViolationCheckListActivity.class,
						R.layout.section_during_elections_bundle_of_ballots),
				new ListViewActivityItem("Голосование на дому",
						ViolationCheckListActivity.class,
						R.layout.section_during_elections_absentee_vote)
		};
	};
}
