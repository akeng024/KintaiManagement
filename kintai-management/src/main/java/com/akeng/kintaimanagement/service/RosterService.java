package com.akeng.kintaimanagement.service;

import com.akeng.kintaimanagement.form.RosterForm;
import com.akeng.kintaimanagement.model.DailyRoster;
import com.akeng.kintaimanagement.repository.RosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.akeng.kintaimanagement.form.RosterForm.toStr;

@Service
public class RosterService {
    @Autowired
    RosterRepository rosterRepository;

    public List<RosterForm> getAll(long userId) {
        List<RosterForm> forms = new ArrayList<>();

        var rosterList = rosterRepository.findAllByUserId(userId);
        for (var rowData : rosterList) {
            var form = new RosterForm(
                    rowData.getPunchDate().getDayOfMonth(),
                    rowData.getPunchDate().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE),
                    toStr(rowData.getPunchInTime()),
                    toStr(rowData.getPunchOutTime()),
                    null,
                    rowData.getWorkingHours(),
                    "01:00",
                    null);
            forms.add(form);
        }
        return forms;
    }

    public void insertPunchIn(LocalDateTime dateTime, long userId) {
        var today = dateTime.toLocalDate();
        var todayRosterOption = rosterRepository.findByPunchDateAndUserId(today, userId);
        if (todayRosterOption.isPresent()) {
            return;
        }

        var todayRoster = new DailyRoster();
        todayRoster.setUserId(userId);
        todayRoster.setPunchDate(dateTime.toLocalDate());
        todayRoster.setPunchInTime(dateTime.toLocalTime());
        rosterRepository.save(todayRoster);
    }

    public void insertPunchOut(LocalDateTime dateTime, long userId) {
        var today = dateTime.toLocalDate();
        var todayRosterOption = rosterRepository.findByPunchDateAndUserId(today, userId);
        if (!todayRosterOption.isPresent()) {
            return;
        }

        var todayRoster = todayRosterOption.get();
        todayRoster.setPunchOutTime(dateTime.toLocalTime());
        rosterRepository.save(todayRoster);

        insertHours(todayRoster);
    }

    private void insertHours(DailyRoster roster) {
        var workingMin = ChronoUnit.MINUTES.between(roster.getPunchInTime(), roster.getPunchOutTime());
        var working = toHour(workingMin);
        roster.setWorkingHours(working);
        rosterRepository.save(roster);
    }

    private String toHour(long minutes) {
        var hour = setZero(minutes / 60);
        var minute = setZero(minutes % 60);
        return hour + ":" + minute;
    }

    private String setZero(long val) {
        var valStr = String.valueOf(val);
        if(val < 10) {
            return String.format("%2s", valStr).replace(" ", "0");
        }
        return valStr;
    }
}
