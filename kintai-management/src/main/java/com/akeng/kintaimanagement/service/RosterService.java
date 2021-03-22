package com.akeng.kintaimanagement.service;

import com.akeng.kintaimanagement.form.DailyRosterForm;
import com.akeng.kintaimanagement.form.MonthlyRosterForm;
import com.akeng.kintaimanagement.model.DailyRoster;
import com.akeng.kintaimanagement.model.MonthlyRoster;
import com.akeng.kintaimanagement.repository.DailyRosterRepository;
import com.akeng.kintaimanagement.repository.MonthlyRosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.akeng.kintaimanagement.form.DailyRosterForm.toHour;
import static com.akeng.kintaimanagement.form.DailyRosterForm.toStr;
import static java.time.temporal.TemporalAdjusters.*;

@Service
public class RosterService {
    @Autowired
    DailyRosterRepository dailyRosterRepository;
    @Autowired
    MonthlyRosterRepository monthlyRosterRepository;

    // 動作確認用: DBに今年1年分の日付をセット
    public void setDays(LocalDateTime today, long userId) {
        var firstDayOfYear = today.toLocalDate().with(firstDayOfYear());
        var lastDayOfYear = today.toLocalDate().with(lastDayOfYear());

        for(int i = 0; i < 370; i++) {
            var d = firstDayOfYear.plusDays(i);

            // 年末より未来の日付だったらループを抜ける
            if(d.isAfter(lastDayOfYear)) {
                break;
            }

            var dr = new DailyRoster();
            dr.setUserId(userId);
            dr.setDate(d);
            dailyRosterRepository.save(dr);
        }
    }

    // 動作確認用: DBに今年1年分の月をセット
    public void setMonths(LocalDateTime today, long userId) {
        for(int i = 1; i < 13; i++) {
            var mr = new MonthlyRoster();
            mr.setUserId(userId);
            mr.setYear(today.getYear());
            mr.setMonth(i);
            monthlyRosterRepository.save(mr);
        }
    }

    public MonthlyRosterForm getMonthlies(LocalDateTime today, long userId) {
        var roster
                = monthlyRosterRepository.findByYearAndMonthAndUserId(today.getYear(), today.getMonthValue(), userId);

        return new MonthlyRosterForm(
                roster.getTotalWorkingDays(),
                roster.getAbsenceDays(),
                roster.getPaidAcquisitionDays(),
                toHour(roster.getTotalWorkingHours()),
                toHour(roster.getTotalOvertimeHours()));
    }

    public List<DailyRosterForm> getDailies(LocalDateTime today, long userId) {
        var allRosters = dailyRosterRepository.findAllByUserId(userId);
        var rosters = allRosters.stream()
                .filter(r -> r.getDate().getMonthValue() == today.getMonthValue())
                .collect(Collectors.toList());

        return rosters.stream()
                .map(r -> new DailyRosterForm(
                            r.getDate().getDayOfMonth(),
                            r.getDate().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE),
                            toStr(r.getPunchInTime()),
                            toStr(r.getPunchOutTime()),
                            toHour(r.getWorkingHours()),
                            null,
                            null,
                            null))
                .collect(Collectors.toList());
    }

    public void insertPunchIn(LocalDateTime today, long userId) {
        var todayRoster = dailyRosterRepository.findByDateAndUserId(today.toLocalDate(), userId);
        if (todayRoster.getPunchInTime() != null) {
            return;
        }

        todayRoster.setPunchInTime(today.toLocalTime());
        dailyRosterRepository.save(todayRoster);
    }

    public void insertPunchOut(LocalDateTime today, long userId) {
        var todayRoster = dailyRosterRepository.findByDateAndUserId(today.toLocalDate(), userId);
        if (todayRoster.getPunchOutTime() != null) {
            return;
        }

        todayRoster.setPunchOutTime(today.toLocalTime());
        var workingHours = ChronoUnit.MINUTES.between(todayRoster.getPunchInTime(), todayRoster.getPunchOutTime());
        todayRoster.setWorkingHours(workingHours);
        dailyRosterRepository.save(todayRoster);

        var monthRoster
                = monthlyRosterRepository.findByYearAndMonthAndUserId(today.getYear(), today.getMonthValue(), userId);
        var totalWorkingHours = monthRoster.getTotalWorkingHours() + workingHours;
        monthRoster.setTotalWorkingHours(totalWorkingHours);
        var totalWorkingDays = monthRoster.getTotalWorkingDays() + 1;
        monthRoster.setTotalWorkingDays(totalWorkingDays);
        monthlyRosterRepository.save(monthRoster);
    }
}
