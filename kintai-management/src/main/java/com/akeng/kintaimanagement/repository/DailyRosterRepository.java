package com.akeng.kintaimanagement.repository;

import com.akeng.kintaimanagement.model.DailyRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyRosterRepository extends JpaRepository<DailyRoster, Long> {
    List<DailyRoster> findAllByUserId(Long userId);
    DailyRoster findByDateAndUserId(LocalDate date, Long userId);
}
