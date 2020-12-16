package com.zengc.core.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    //注释原因 SimpleDateFormat为非线程安全，多线程或for循环中连续调用有极大可能报错

    private static final String PATTERN_1 = "yyyy-MM-dd";
    private static final String PATTERN_2 = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_3 = "yyyyMMddHHmmss";
    private static final String PATTERN_4 = "HH:mm:ss";
    private static final String PATTERN_5 = "yyyyMMdd";


    /**
     * 获得当前时间
     *
     * @param index 0：yyyy-MM-dd
     * @param index 1：yyyy-MM-dd HH:mm:ss
     * @param index 2：yyyyMMddHHmmss
     * @param index 3：HH:mm:ss
     * @param index 4：yyyyMMdd
     * @return
     * @author dingzd
     */
    public static String getNowDate(int index) {
        try {
            if (index == 0) {
                SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
                return sdf1.format(new Date());
            } else if (index == 1) {
                SimpleDateFormat sdf2 = new SimpleDateFormat(PATTERN_2);
                return sdf2.format(new Date());
            } else if (index == 2) {
                SimpleDateFormat sdf3 = new SimpleDateFormat(PATTERN_3);
                return sdf3.format(new Date());
            } else if (index == 3) {
                SimpleDateFormat sdf4 = new SimpleDateFormat(PATTERN_4);
                return sdf4.format(new Date());
            } else if (index == 4) {
                SimpleDateFormat sdf4 = new SimpleDateFormat(PATTERN_5);
                return sdf4.format(new Date());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算两个日期之间相距多少天
     *
     * @param begin
     * @param end
     * @return
     */
    public static int getBetweenTime1AndTime2(String begin, String end) {
        long to = 0;
        long from = 0;
        int timeNum = 0;
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
        try {
            to = sdf1.parse(begin).getTime();
            from = sdf1.parse(end).getTime();
            timeNum = (int) ((from - to) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeNum;
    }

    /**
     * 得到当前时间的具体的年月日周
     *
     * @return
     * @author pany
     */
    @SuppressWarnings("static-access")
    public static Map<String, Integer> getDateMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Calendar can = Calendar.getInstance();
        int year = can.get(Calendar.YEAR);
        int month = can.get(Calendar.MONTH) + 1;
        int day = can.get(Calendar.DAY_OF_MONTH);
        int week = can.get(Calendar.DAY_OF_WEEK) - can.getFirstDayOfWeek();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("week", week);
        return map;
    }

    /**
     * 得到几天后的时间
     */

    public static String getDateAfter(String d, int day) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
        Calendar now = Calendar.getInstance();
        Date date = stringToDate(d);
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return sdf1.format(now.getTime());
    }

    /**
     * 得到几天前的时间
     */

    public static String getDateBefore(String d, int day) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
        Calendar now = Calendar.getInstance();
        Date date = stringToDate(d);
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return sdf1.format(now.getTime());
    }

    public static String getDateBefore(Date date, int day) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return sdf1.format(now.getTime());
    }

    public static String getDateBeforeByApp(String d, int day) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_2);
        Calendar now = Calendar.getInstance();
        Date date = stringToDate(d);
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return sdf1.format(now.getTime());
    }

    /**
     * 得到几天前的时间,偏移多少分钟
     */

    public static String getDateBefore(String d, int day, int minute) {
        Calendar now = new GregorianCalendar();
        SimpleDateFormat sdf2 = new SimpleDateFormat(PATTERN_2);
        Date date = null;
        try {
            date = sdf2.parse(d);
        } catch (ParseException e) {
        }
        now.setTime(date);
        now.add(Calendar.DATE, -day);
        now.add(Calendar.MINUTE, minute);
        return sdf2.format(now.getTime());
    }

    /**
     * string类型转换成date
     *
     * @param dateStr
     * @return
     * @author pany
     */
    public static Date stringToDate(String dateStr) {
        Date date = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
        try {
            date = sdf1.parse(dateStr);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return date;
    }

    /**
     * date转换成String
     *
     * @param index 0：yyyy-MM-dd
     * @param index 1：yyyy-MM-dd HH:mm:ss
     * @return
     * @author pany
     */
    public static String dateTostring(Date date, int index) {

        if (date == null) {
            return "";
        }
        if (index == 0) {
            SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
            return sdf1.format(date);
        } else if (index == 1) {
            SimpleDateFormat sdf2 = new SimpleDateFormat(PATTERN_2);
            return sdf2.format(date);
        }
        return "";
    }

    /**
     * @param
     * @return 根据生日获取年龄
     */
    public static String getAge(String birthDayS) {
        Date birthDay = stringToDate(birthDayS);
        return getAge(birthDay);
    }
    public static String getAge(Date birthDay) {
        if (birthDay==null){
           return "";
        }
        Calendar cal = Calendar.getInstance();

        //Date birthDay = stringToDate(birthDayS);
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow <= dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        if(age < 0 ){
            age = 0;
        }
        return age + "";
    }

    public static boolean getDistanceDays(String str1, String str2) {
        Date one = new Date();
        Date two = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat(PATTERN_1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(PATTERN_2);
        try {
            if (str1.length() == 10) {
                one = sdf1.parse(str1);
            } else if (str1.length() == 19) {
                one = sdf2.parse(str1);
            }
            if (str2.length() == 10) {
                two = sdf1.parse(str2);
            } else if (str2.length() == 19) {
                two = sdf2.parse(str2);
            }
            long time1 = one.getTime();
            long time2 = two.getTime();
            if (time1 > time2) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatDate(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_1);
        return dateFormat.format(date);
    }
    /**
     * @description 时间转换
     * @author dingzd
     * @date 2019/6/27
     * @param date
     * @param pattern1 "yyyyMMdd"
     * @param pattern2 "yyyy-MM-dd"
     * @remark
     * @version V1.0
     */
    public static String formatDate(String date,String pattern1,String pattern2){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern1);
        formatter.setLenient(false);
        Date newDate= null;
        try {
            newDate = formatter.parse(date);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        formatter = new SimpleDateFormat(pattern2);
        return formatter.format(newDate);
    }
    public static void main(String[] args) {

        System.out.println("__________" + DateUtils.getDateBefore("2019-05-13", 1));
        System.out.println("__________" + DateUtils.getDateBefore("2019-05-13", 7));
        System.out.println("__________" + DateUtils.getDateBefore("2019-05-13", 30));
    }

    public static String getResult(String time) {
        Date time1 = new Date();
        Date time2 = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_2);
            time1 = sdf.parse(time);
            time2 = sdf.parse(getNowDate(1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long days = time2.getTime() - time1.getTime();//86400000
        int i = 0;
        if (days < 60000) {
            return "刚刚";
        }
        if (days < 3600000 && days >= 60000) {
            i = Integer.valueOf(String.valueOf(days / 60000));
            return i + "分钟前";
        }
        if (days >= 3600000 && days < 86400000) {
            i = Integer.valueOf(String.valueOf(days / 3600000));
            return i + "小时前";
        }
        if (days >= 86400000) {
            i = Integer.valueOf(String.valueOf(days / 86400000));
            if (i >= 30) {
                if ((i / 30) > 12) {
                    return (i / 30) / 12 + "年前";
                }
                return i / 30 + "月前";
            }
            return i + "天前";
        }
        return "";
    }

    //获取新时间
    public static String compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat(PATTERN_2);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return date1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return date2;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return date2;
    }

    /**
     * 截止日期
     *
     * @param injectTime
     * @param month
     * @return String
     */
    public static String getDate(String injectTime, String month) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_2);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(injectTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH, Integer.parseInt(month));
        Date date1 = cal.getTime();
        String activateExpiredTime = sdf.format(date1);
        return activateExpiredTime;
    }

    public static boolean compareTodata(String date1, String date2) {
        DateFormat df = new SimpleDateFormat(PATTERN_1);
        boolean flag = false;
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                flag = true;
            } else if (dt1.getTime() < dt2.getTime()) {
                flag = false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return flag;
    }

}
