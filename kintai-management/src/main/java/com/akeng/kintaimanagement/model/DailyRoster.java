package com.akeng.kintaimanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class DailyRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDate date;
    private LocalTime punchInTime;
    private LocalTime punchOutTime;
    private long workingHours;
    private long breakHours;
    private long overtimeHours;
    private String workStatus;
}
