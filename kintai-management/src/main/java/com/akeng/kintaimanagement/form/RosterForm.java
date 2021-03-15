package com.akeng.kintaimanagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class RosterForm {
    private Integer day;
    private String dayOfTheWeek;
    private String punchInTime;
    private String punchOutTime;
    private String notices;
    private String workingHours;
    private String breakHours;
    private String overtimeHours;

    public static String toStr(LocalTime localTime) {
        if(localTime == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(dateTimeFormatter);
    }
}
