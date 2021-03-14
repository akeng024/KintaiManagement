package com.akeng.kintaimanagement.service;

import com.akeng.kintaimanagement.model.DailyRoster;
import com.akeng.kintaimanagement.repository.PunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PunchService {
    @Autowired
    PunchRepository punchRepository;

    public void insertPunchIn(LocalDateTime dateTime) {
        var dailyRoster = new DailyRoster();
        dailyRoster.setPunchDate(dateTime.toLocalDate());
        dailyRoster.setPunchInTime(dateTime.toLocalTime());
        punchRepository.save(dailyRoster);
    }

    public void insertPunchOut(LocalDateTime dateTime) {
        var today = dateTime.toLocalDate();
        var todayRosterOption = punchRepository.findByPunchDate(today);
        if(!todayRosterOption.isPresent()) {

        }
        var todayRoster = todayRosterOption.get();
        todayRoster.setPunchOutTime(dateTime.toLocalTime());
        punchRepository.save(todayRoster);
    }
}
