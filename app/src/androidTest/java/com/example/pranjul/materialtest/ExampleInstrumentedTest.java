package com.example.pranjul.materialtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Instrumentation activity_main2, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under activity_main2.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.pranjul.materialtest", appContext.getPackageName());
    }
}
