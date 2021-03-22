package com.akeng.kintaimanagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyRosterForm {
    private long totalWorkingDays;
    private long absenceDays;
    private long paidAcquisitionDays;
    private String totalWorkingHours;
    private String totalOvertimeHours;
}
