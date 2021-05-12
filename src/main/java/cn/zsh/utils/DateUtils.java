package cn.zsh.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class DateUtils {
    public enum Pattern {
        CST("yyyy-MM-dd HH:mm:ss.SSS", "GMT+8:00");
        private String val;
        private String zone;
        Pattern(String val, String zone) {
            this.val = val;
            this.zone = zone;
        }
        public String getVal() {
            return val;
        }
        public String getZone() {
            return zone;
        }
    }

    public static Date parse(String str, Pattern pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getVal(), Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(pattern.getZone()));
        return simpleDateFormat.parse(str);
    }

    public static String format(Date date, Pattern pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getVal(), Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(pattern.getZone()));
        return simpleDateFormat.format(date);
    }


    public static String beforeOneHourToNowDate(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        /* MINUTE 指示一天中的某分 */
        calendar.set(Calendar.MINUTE,(calendar.get(Calendar.MINUTE) - minute));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  df.format(calendar.getTime());
    }
}
