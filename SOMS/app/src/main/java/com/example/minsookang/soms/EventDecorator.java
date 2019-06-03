package com.example.minsookang.soms;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

/**
 * Decorate several days with a dot
 */
public class EventDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator(int color, Collection<CalendarDay> dates,Activity context, String UserClass) {
        Log.d("EventUserClass", UserClass);
        if(Integer.parseInt(UserClass) == 0) {
            drawable = context.getResources().getDrawable(R.drawable.greencheck);
            Log.d("Userclass SOldier", "SOldier");
            this.color = color;
            this.dates = new HashSet<>(dates);
        }
        else{
            drawable = context.getResources().getDrawable(R.drawable.redx);
            this.color = color;
            Log.d("Userclass Commander", "SOldier");
            this.dates = new HashSet<>(dates);
        }
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
        //view.addSpan(new DotSpan(5, color)); // 날자밑에 점
    }
}