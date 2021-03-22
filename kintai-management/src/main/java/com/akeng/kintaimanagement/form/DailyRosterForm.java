package com.akeng.kintaimanagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class DailyRosterForm {
    private Integer day;
    private String dayOfTheWeek;
    private String punchInTime;
    private String punchOutTime;
    private String workingHours;
    private String breakHours;
    private String overtimeHours;
    private String workStatus;

    public static String toStr(LocalTime localTime) {
        if(localTime == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(dateTimeFormatter);
    }

    public static String toHour(long minutes) {
        var hour = setZero(minutes / 60);
        var minute = setZero(minutes % 60);
        return hour + ":" + minute;
    }

    private static String setZero(long val) {
        var valStr = String.valueOf(val);
        if(val < 10) {
            return String.format("%2s", valStr).replace(" ", "0");
        }
        return valStr;
    }
}
