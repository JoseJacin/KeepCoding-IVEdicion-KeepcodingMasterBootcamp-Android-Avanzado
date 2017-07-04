package com.josejacin.madridshops.domain.model;

import java.util.LinkedList;
import java.util.List;

public class Activities implements ActivitiesIterable, ActivitiesUpdatable {
    // Properties
    private List<Activity> activities;

    public Activities() {
    }

    // Lazy getter.
    // Se reserva memoria solo cuando se va a necesitar el objeto
    private List<Activity> getActivities() {
        if (activities == null) {
            activities = new LinkedList<>();
        }
        return activities;
    }

    @Override
    public void add(Activity activity) {
        getActivities().add(activity);
    }

    @Override
    public void delete(Activity activity) {
        getActivities().remove(activity);
    }

    @Override
    public void update(Activity newActivity, long index) {

    }

    @Override
    public long size() {
        return getActivities().size();
    }

    @Override
    public Activity get(long index) {
        return getActivities().get((int) index);
    }

    @Override
    public List<Activity> allActivities() {
        // Se realiza una copia inmutable de la lista de activities para evitar que desde fuera se pueda
        // modificar sin parar por los métodos de modificación (add, delete, update, ...)
        List<Activity> listCopy = new LinkedList<>();

        for (Activity activity : getActivities()) {
            listCopy.add(activity);
        }

        return listCopy;
    }


}
