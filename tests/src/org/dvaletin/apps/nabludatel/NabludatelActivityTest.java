package org.dvaletin.apps.nabludatel;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class org.dvaletin.apps.nabludatel.NabludatelActivityTest \
 * org.dvaletin.apps.nabludatel.tests/android.test.InstrumentationTestRunner
 */
public class NabludatelActivityTest extends ActivityInstrumentationTestCase2<NabludatelActivity> {

    public NabludatelActivityTest() {
        super("org.dvaletin.apps.nabludatel", NabludatelActivity.class);
    }

}
