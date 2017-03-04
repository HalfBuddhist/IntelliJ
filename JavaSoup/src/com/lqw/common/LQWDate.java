package com.lqw.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LQWDate extends Date {

    /**
     * < constant variables
     */
    public static final long DAY_TIME_INTERVAL = 86400000; //in milliseconds
    public static final long WEEK_TIME_INTERVAL = 1000 * 3600 * 24 * 7; //in milliseconds

    private static DateFormat yyyyMMddHHmmss_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public LQWDate(long timeInervals) {
        // TODO Auto-generated constructor stub
        super(timeInervals);
    }

    public LQWDate() {
        super();
    }

    public static LQWDate dateFromyyyyMMddHHmmssString(String _p_str_timestamp) {
        // TODO Auto-generated constructor stub
        try {
            return (new LQWDate(yyyyMMddHHmmss_format.parse(_p_str_timestamp).getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public String yyyyMMddHHmmssString() {
        return yyyyMMddHHmmss_format.format(this);
    }

    public LQWDate dayBeginning() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        long lTimeCross = ((((hours * 60) + minutes) * 60) + seconds) * 1000;
        return new LQWDate(this.getTime() - lTimeCross);
    }

    public LQWDate nextDay() {
        return new LQWDate(this.getTime() + DAY_TIME_INTERVAL);
    }

    public LQWDate nextNDay(int pNextN) {
        return new LQWDate(this.getTime() + pNextN * DAY_TIME_INTERVAL);
    }

    public LQWDate lastDay() {
        return new LQWDate(this.getTime() - DAY_TIME_INTERVAL);
    }

    public LQWDate lastNDay(int pLastN) {
        return new LQWDate(this.getTime() - pLastN * DAY_TIME_INTERVAL);
    }

    public boolean isDayEqualTo(Date pDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(pDate);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);

        if (year == year1 && month == month1 && day == day1)
            return true;
        else
            return false;
    }


    /**
     * get the distance to the specific date
     *
     * @param pDate
     * @return
     */
    public long dayDistanceTo(LQWDate pDate) {
        return (this.dayBeginning().getTime() - pDate.dayBeginning().getTime()) / DAY_TIME_INTERVAL;
    }

    public int getYear() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this);
        return calendar.get(Calendar.YEAR);
    }

    public LQWDate nextMonday() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.dayBeginning());
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        int next_days = (9 - day_of_week) == 8 ? 1 : (9 - day_of_week);
        return new LQWDate(this.getTime() + next_days * DAY_TIME_INTERVAL);

		/*		
		1 1 8
		2 7
		3 6
		4 5	
		5 4	
		6 3
		7 2
		*/
    }

}
