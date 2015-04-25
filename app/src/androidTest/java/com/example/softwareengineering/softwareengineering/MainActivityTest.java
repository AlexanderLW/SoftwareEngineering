package com.example.softwareengineering.softwareengineering;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

/**
 * Created by User on 4/23/15.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent mLaunchIntent;
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

        mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), MainActivity.class);
        startActivity(mLaunchIntent, null, null);
        final Button beginButton =
                (Button) getActivity()
                        .findViewById(R.id.begin);

    }



}
