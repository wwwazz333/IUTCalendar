package com.iutcalendar.alarm.condition;

import com.iutcalendar.calendrier.EventCalendrier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AlarmCondtion implements Serializable {

    /**
     * contien les jour ou l'alarm s'active
     *
     * @exmple GregorianCalendar.MONDAY
     */
    private final ArrayList<Integer> daysEnabled;
    private long beging, end, alarmAt;//les borne sont comprise (hours in millis)


    public AlarmCondtion(long beging, long end, long alarmAt, ArrayList<Integer> daysEnabled) {
        this.beging = beging;
        this.end = end;
        this.alarmAt = alarmAt;
        this.daysEnabled = daysEnabled;
    }


    public AlarmCondtion(long beging, long end, long alarmAt) {
        this(beging, end, alarmAt, new ArrayList<>());
        daysEnabled.add(GregorianCalendar.MONDAY);
        daysEnabled.add(GregorianCalendar.TUESDAY);
        daysEnabled.add(GregorianCalendar.WEDNESDAY);
        daysEnabled.add(GregorianCalendar.THURSDAY);
        daysEnabled.add(GregorianCalendar.FRIDAY);
    }

    public long getAlarmAt() {
        return alarmAt;
    }

    public void setAlarmAt(long alarmAt) {
        this.alarmAt = alarmAt;
    }

    public long getBeging() {
        return beging;
    }

    public void setBeging(long beging) {
        this.beging = beging;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public ArrayList<Integer> getDaysEnabled() {
        return daysEnabled;
    }


    public boolean isApplicableTo(EventCalendrier event) {
        long dateEventMillis = event.getDate().getHourInMillis();
        return beging <= dateEventMillis && dateEventMillis <= end &&
                daysEnabled.contains(event.getDate().get(GregorianCalendar.DAY_OF_WEEK));
    }
}
