package com.akeng.kintaimanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class MonthlyRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int year;
    private int month;
    private long totalWorkingDays;
    private long totalWeekdayWorkingDays;
    private long totalHolidayWorkingDays;
    private long absenceDays;
    private long latePunchInDays;
    private long earlyPunchOutDays;
    private long paidAcquisitionDays;
    private long totalWorkingHours;
    private long totalOvertimeHours;
    private long totalWeekdayWorkingHours;
    private long totalWeekdayOvertimeHours;
    private long totalHolidayWorkingHours;
    private long totalHolidayOvertimeHours;
    private int approvalStatus;
    private int approvalUserId;
}
