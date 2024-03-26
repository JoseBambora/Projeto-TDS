package com.ruirua.sampleguideapp;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.ruirua.sampleguideapp.repositories.PinRepository;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.ruirua.sampleguideapp", appContext.getPackageName());
    }

    @Test
    public void testPins() {
        Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
        assertNotNull(application);
        PinRepository pr = new PinRepository(application);
        pr.getAllPins().observe((LifecycleOwner) getInstrumentation().getTargetContext(), data -> {
            assertTrue(data.size() > 0);
        });
    }
}