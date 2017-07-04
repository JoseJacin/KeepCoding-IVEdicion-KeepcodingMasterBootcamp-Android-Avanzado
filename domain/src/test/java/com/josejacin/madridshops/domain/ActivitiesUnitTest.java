package com.josejacin.madridshops.domain;

import com.josejacin.madridshops.domain.model.Activities;
import com.josejacin.madridshops.domain.model.Activity;
import com.josejacin.madridshops.domain.model.Shop;
import com.josejacin.madridshops.domain.model.Shops;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
