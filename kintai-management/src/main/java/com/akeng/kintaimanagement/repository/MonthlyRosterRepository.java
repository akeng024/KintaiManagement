package com.akeng.kintaimanagement.repository;

import com.akeng.kintaimanagement.model.MonthlyRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyRosterRepository extends JpaRepository<MonthlyRoster, Long> {
    List<MonthlyRoster> findByUserId(Long userId);
    MonthlyRoster findByYearAndMonthAndUserId(int year, int month, Long userId);
}
