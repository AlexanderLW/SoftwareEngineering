package com.example.softwareengineering.softwareengineering;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
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


    @LargeTest
    public void testSavedSolutionSequence(){
        testBeginButtonNotNull();



    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }



}
