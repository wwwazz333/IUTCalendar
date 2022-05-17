package com.iutcalendar.settings;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import com.calendar.iutcalendar.R;
import com.iutcalendar.data.DataGlobal;

import java.util.Locale;

public class SettingsApp {
    public static Configuration setLocale(Resources resources, String language) {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        //Update configuration
        resources.updateConfiguration(configuration, metrics);
        return configuration;
    }

    public static Locale getLocale(Resources resources) {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        return configuration.locale;
    }

    public static void adapteTheme(Context context) {


        String t = DataGlobal.getTheme(context);
        Log.d("Theme", t);
        if (t.equals("black")) {
            context.setTheme(R.style.Theme_IUTCalendarNight);
        } else if (t.equals("light")) {
            context.setTheme(R.style.Theme_IUTCalendarLight);
        } else {
            context.setTheme(R.style.Theme_IUTCalendar);
        }

    }

    public static @ColorInt int getColor(int id, Activity activity) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = activity.getTheme();
        theme.resolveAttribute(id, typedValue, true);
        @ColorInt int color = typedValue.data;
        return color;
    }
}