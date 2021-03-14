package com.akeng.kintaimanagement.service;

import com.akeng.kintaimanagement.model.DailyRoster;
import com.akeng.kintaimanagement.repository.RosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RosterService {
    @Autowired
    RosterRepository rosterRepository;

    public List<DailyRoster> getAll(){
        return rosterRepository.findAll();
    }

    public void insertPunchIn(LocalDateTime dateTime) {
        var today = dateTime.toLocalDate();
        var todayRosterOption = rosterRepository.findByPunchDate(today);
        if(todayRosterOption.isPresent()) {
            return;
        }
        var todayRoster = todayRosterOption.get();
        todayRoster.setPunchDate(dateTime.toLocalDate());
        todayRoster.setPunchInTime(dateTime.toLocalTime());
        rosterRepository.save(todayRoster);
    }

    public void insertPunchOut(LocalDateTime dateTime) {
        var today = dateTime.toLocalDate();
        var todayRosterOption = rosterRepository.findByPunchDate(today);
        if(!todayRosterOption.isPresent()) {
            return;
        }
        var todayRoster = todayRosterOption.get();
        todayRoster.setPunchOutTime(dateTime.toLocalTime());
        rosterRepository.save(todayRoster);
    }
}
