package com.josejacin.madridshops.domain;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.josejacin.madridshops.domain.managers.db.ActivityDAO;
import com.josejacin.madridshops.domain.model.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ActivityDAOTest {
    final static Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void given_activity_DAO_inserts_activity() throws Exception {
        ActivityDAO sut = new ActivityDAO(appContext);
        Activity activity = Activity.of(1, "Activity test")
                .setAddress("address")
                .setLatitude(10)
                .setLongitude(11);

        long id = sut.insert(activity);

        assertTrue(id > 0);
    }
}
