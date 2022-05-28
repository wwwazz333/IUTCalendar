package com.iutcalendar.data;

import android.content.Context;
import androidx.preference.PreferenceManager;

public class DataGlobal {
    public final static String URL = "url_path";
    public final static String LANGUAGUE = "language_selected";
    public final static String THEME = "theme_selected";
    public final static String THEME_RES_WIDGET = "theme_widget_selected";
    public final static String ALARM_ENABELED = "alarme_enable";

    public final static String ALARM_RING_TIME_BEFOR = "alarm_ring_time_before";

    public static String getTheme(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(THEME, "default");
    }

    public static String getThemeResWidget(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(THEME_RES_WIDGET, "grey");
    }


    public static String getLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(LANGUAGUE, "fr");
    }

    //String
    public static void save(Context context, String key, String data) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, data).commit();
    }

    public static String getSavedString(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
    }

    //boolean
    public static void save(Context context, String key, boolean data) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, data).commit();
    }

    public static boolean getSavedBoolean(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
    }

    //boolean
    public static void save(Context context, String key, int data) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, data).commit();
    }

    public static int getSavedInt(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
    }


    public static void savePath(Context context, String data) {
        save(context, URL, data);
    }

    public static String getSavedPath(Context context) {
        return getSavedString(context, URL);
    }


    public static long getAlarmRingTimeBefore(Context context) {
        return DataGlobal.getSavedInt(context, DataGlobal.ALARM_RING_TIME_BEFOR) * 60_000L;
    }

}
