package tim.com.libnetwork.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/5/21
 */
public class DateTimeUtil {
    private DateTimeUtil(){}

    public static int getAge(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        Integer age = yearNow - year;
        if (monthNow <= month) {
            if (monthNow == month) {
                if (dayOfMonthNow < day) {
                    age--;
                }
            } else {
                age--;
            }
        }
        if(age < 1) age = 1;
        return age;
    }

    public static String getAge(String birthDay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(birthDay);

            Calendar cal = Calendar.getInstance();

            if (cal.before(date)) {
                throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(date);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            Integer age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    age--;
                }
            }
            if(age < 1) age = 1;
            return age.toString();
        } catch (Exception e) {

        }
        return null;
    }

    public static Calendar getCreatTime(String birthDay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse(birthDay);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal;
        } catch (Exception e) {

        }
        return null;
    }

    public static Calendar getCreatTime2(String birthDay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(birthDay);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal;
        } catch (Exception e) {

        }
        return null;
    }

    public static String getBetweenTime(Long dateTime) {
        Date date = new Date();
        long time;
        if(String.valueOf(dateTime).length() == 10) {
            String temp = String.valueOf(dateTime) + "000";
            dateTime = dateTime * 1000;
            time = (date.getTime() - Long.parseLong(temp))/(1000*3600*24);
        } else {
            time = (date.getTime() - dateTime)/(1000*3600*24);
        }
        if (time > 0) {
            if(time < 7) {
                return (String.valueOf(time) + "天前");
            } else {
                if(date.getYear() == new Date(dateTime).getYear()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("M-dd");
                    return sdf.format(new Date(dateTime));
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                return sdf.format(new Date(dateTime));
            }
        } else {
            time = (date.getTime() - dateTime)/(1000*3600);
            if(time > 0) {
                return (String.valueOf(time) + "小时前");
            } else {
                time = (date.getTime() - dateTime)/(1000*60);
                return (String.valueOf(time) + "分钟前");
            }
        }
    }

    public static String getPublishTimes(Long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(dateTime));
    }

    public static String getPublishTime(Long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(new Date(dateTime));
    }

    public static String getPublishPointTime(Long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(new Date(dateTime));
    }

    public static String getPayTime(Long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(dateTime));
    }

    public static String getDetailPayTime(Long dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(dateTime));
    }

    public static String getCommentTime(Long commentTime) {
        Date date = new Date();
        Date commentDate = new Date(commentTime);
        try {
            if(date.getYear() == commentDate.getYear()) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                return sdf.format(commentDate);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.format(commentDate);
        }catch (Exception e) {

        }
        return null;
    }

    public static String getCommentTime24(Long commentTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            Date date = new Date(commentTime);

            return sdf.format(date);
        }catch (Exception e) {

        }
        return null;
    }

    public static String getSnapUpTime(Long commentTime) {
        try {
            long days = commentTime / (1000 * 60 * 60 * 24);
            long hours = (commentTime-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (commentTime-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            long seconds = (commentTime-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60) - minutes * (1000 * 60)) / 1000;

            if(days > 0) {
                return String.format("大于%s天", days);
            } else if(hours > 0) {
                return String.format("大于%s小时", hours);
            } else if (minutes > 0) {
                return String.format("剩余%s分", minutes);
            } else {
                return String.format("剩余%s秒", seconds);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static boolean checkAdvertTime(long advertTime, long currentTime) {
        if(advertTime == 0) {
            return true;
        }
        Calendar advertCal = Calendar.getInstance();
        advertCal.setTimeInMillis(advertTime);
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTimeInMillis(currentTime);
        if(advertCal.get(Calendar.YEAR) != currentCal.get(Calendar.YEAR)
                || advertCal.get(Calendar.MONTH) != currentCal.get(Calendar.MONTH)
                || advertCal.get(Calendar.DAY_OF_MONTH) != currentCal.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else if ((advertCal.get(Calendar.HOUR_OF_DAY) >=0 && advertCal.get(Calendar.HOUR_OF_DAY) < 12)
                && (currentCal.get(Calendar.HOUR_OF_DAY) >=12 && currentCal.get(Calendar.HOUR_OF_DAY) <= 23)){
            return true;
        }
        return false;
    }

    public static String getCountDownTime(Long commentTime) {
        try {
            long days = commentTime / (1000 * 60 * 60 * 24);
            long hours = (commentTime-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (commentTime-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            long seconds = (commentTime-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60) - minutes * (1000 * 60)) / 1000;

            if(days > 0) {
                return String.format("大于%s天", days);
            } else if(hours > 0) {
                return String.format("大于%s小时", hours);
            } else if (minutes > 0) {
                return String.format("%s分%s秒", minutes, seconds);
            } else {
                return String.format("%s秒", seconds);
            }
        } catch (Exception e) {

        }
        return null;
    }
}
