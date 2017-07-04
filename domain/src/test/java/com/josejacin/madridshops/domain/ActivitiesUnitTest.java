package com.josejacin.madridshops.domain;

import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ActivitiesUnitTest {
    @Test
    public void after_creation_activities_size_is_zero() throws Exception {
        Activities sut = new Activities();

        assertEquals(0, sut.size());
    }

    @Test
    public void activities_adding_one_activity_size_is_one() throws Exception {
        Activities sut = new Activities();

        sut.add(Activity.of(1, "My activity"));

        assertEquals(1, sut.size());
    }

    @Test
    public void activities_adding_one_activity_and_deleting_size_is_zero() throws Exception {
        Activities sut = new Activities();

        Activity activity = Activity.of(1, "My activity");
        sut.add(activity);
        sut.delete(activity);

        assertEquals(0, sut.size());
    }

    @Test
    public void activities_adding_one_activity_and_getting_returns_that_activity() throws Exception {
        Activities sut = new Activities();

        Activity activity = Activity.of(1, "My activity");
        sut.add(activity);
        Activity activity1 = sut.get(0);

        assertEquals(activity.getId(), activity1.getId());
        assertEquals(activity.getName(), activity1.getName());
    }

    @Test
    public void activities_adding_several_activities_returns_all_activities() throws Exception {
        Activities sut = new Activities();

        for (int i = 0; i < 10; i++) {
            Activity activity = Activity.of(i, "My activity " + i);
            sut.add(activity);
        }

        assertEquals(10, sut.size());
        assertEquals(10, sut.allActivities().size());
        assertEquals(0, sut.allActivities().get(0).getId());
        assertEquals("My activity 0", sut.allActivities().get(0).getName());
    }

    @Test
    public void activities_updating_one_activity_and_getting_returns_that_modify_activity() throws Exception {
        Activities sut = new Activities();

        for (int i = 0; i < 10; i++) {
            Activity activity = Activity.of(i, "My activity " + i);
            sut.add(activity);
        }

        sut.update(Activity.of(5, "My updated activity"),5);

        assertNotEquals("My updated activity", sut.get(4).getName());
        assertEquals("My updated activity", sut.get(5).getName());
        assertNotEquals("My updated activity", sut.get(6).getName());
    }
}
