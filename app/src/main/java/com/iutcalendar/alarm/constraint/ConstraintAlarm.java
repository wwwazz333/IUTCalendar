package com.iutcalendar.alarm.constraint;

import com.iutcalendar.alarm.constraint.label_constraint.ConstraintLabelAlarm;
import com.iutcalendar.calendrier.EventCalendrier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class ConstraintAlarm implements Serializable {

    private long beging, end, alarmAt;//les borne sont comprise (hours in millis)

    /**
     * contien les jour ou l'alarm s'active
     *
     * @exmple GregorianCalendar.MONDAY
     */
    private ArrayList<Integer> daysEnabled;

    private List<ConstraintLabelAlarm> constraintLabels;

    public ConstraintAlarm(long beging, long end, long alarmAt, ArrayList<Integer> daysEnabled, List<ConstraintLabelAlarm> constraintLabels) {
        this.beging = beging;
        this.end = end;
        this.alarmAt = alarmAt;
        this.daysEnabled = daysEnabled;
        this.constraintLabels = constraintLabels;
    }

    public ConstraintAlarm(long beging, long end, long alarmAt, ArrayList<Integer> daysEnabled) {
        this(beging, end, alarmAt, daysEnabled, new ArrayList<>());
    }
    public ConstraintAlarm(long beging, long end, long alarmAt) {
        this(beging, end, alarmAt, null, new ArrayList<>());
        this.daysEnabled = new ArrayList<>();
        daysEnabled.add(GregorianCalendar.MONDAY);
        daysEnabled.add(GregorianCalendar.TUESDAY);
        daysEnabled.add(GregorianCalendar.WEDNESDAY);
        daysEnabled.add(GregorianCalendar.THURSDAY);
        daysEnabled.add(GregorianCalendar.FRIDAY);
    }

    public void addConstraint(ConstraintLabelAlarm constraint) {
        constraintLabels.add(constraint);
    }
    public void addConstraint(ConstraintLabelAlarm.Containing typeDeContraint, String contraintRegex) {
        constraintLabels.add(new ConstraintLabelAlarm(this, typeDeContraint, contraintRegex));
    }

    public long getAlarmAt() {
        return alarmAt;
    }

    public long getBeging() {
        return beging;
    }

    public long getEnd() {
        return end;
    }

    public void setBeging(long beging) {
        this.beging = beging;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setAlarmAt(long alarmAt) {
        this.alarmAt = alarmAt;
    }

    public ArrayList<Integer> getDaysEnabled() {
        return daysEnabled;
    }

    public List<ConstraintLabelAlarm> getConstraintLabels() {
        return constraintLabels;
    }

    public void removeConstraint(int index) {
        Iterator<ConstraintLabelAlarm> it = constraintLabels.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            if (i == index) {
                it.remove();
                return;
            }
            i++;
        }
    }
    public void removeConstraint(ConstraintLabelAlarm constraintLabelAlarm){
        constraintLabels.remove(constraintLabelAlarm);
    }

    public boolean isApplicableTo(EventCalendrier event) {
        long dateEventMillis = event.getDate().getHourInMillis();
        if (beging <= dateEventMillis && dateEventMillis <= end &&
                daysEnabled.contains(event.getDate().get(GregorianCalendar.DAY_OF_WEEK))) {
            //check si respect contrainte label
            for (ConstraintLabelAlarm constraint : constraintLabels) {
                if (!constraint.getContraintRegex().isEmpty()) {
                    switch (constraint.getTypeDeContraint()) {
                        case MUST_CONTAIN:
                            if (!event.getSummary().matches(constraint.getContraintRegex())) {
                                return false;
                            }
                            break;
                        case MUST_NOT_CONTAIN:
                            if (event.getSummary().matches(constraint.getContraintRegex())) {
                                return false;
                            }
                            break;
                        case MUST_BE_EXACTLY:
                            if (!event.getSummary().equals(constraint.getContraintRegex())) {
                                return false;
                            }
                            break;
                        case MUST_NOT_BE_EXACTLY:
                            if (event.getSummary().equals(constraint.getContraintRegex())) {
                                return false;
                            }
                            break;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
