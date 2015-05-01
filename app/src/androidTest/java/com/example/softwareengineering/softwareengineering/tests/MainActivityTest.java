package com.example.softwareengineering.softwareengineering.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import com.example.softwareengineering.softwareengineering.MainActivity;
import com.example.softwareengineering.softwareengineering.R;
import junit.framework.Assert;

/**
 * Created by User on 4/23/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    /**
     * Determines if the MainActivity class launches
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception{
        super.setUp();

    }

    @SmallTest
    public void testBeginButtonNotNull(){
        final Button beginButton =
                (Button) getActivity()
                        .findViewById(R.id.begin);
        Assert.assertNotNull(beginButton);
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }



}
