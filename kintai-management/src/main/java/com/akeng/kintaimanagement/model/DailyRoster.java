package com.akeng.kintaimanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class DailyRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDate punchDate;
    private LocalTime punchInTime;
    private LocalTime punchOutTime;
    private String workingHours;
    private String breakHours;
    private String overtimeHours;
}
