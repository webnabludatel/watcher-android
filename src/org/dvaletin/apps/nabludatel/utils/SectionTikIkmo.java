package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.R;
import org.dvaletin.apps.nabludatel.ViolationCheckListActivity;

public class SectionTikIkmo extends SectionList {
	public SectionTikIkmo() {
		listItems = new ListViewActivityItem[] {
				new ListViewActivityItem(
						"Помещение ТИК", 
						ViolationCheckListActivity.class,
						R.layout.section_tik_ikmo_district
				),
				new ListViewActivityItem(
						"Занесение данных в УФСТ", 
						ViolationCheckListActivity.class,
						R.layout.section_tik_ikmo_tik_processing
				),
		};
	}

}
