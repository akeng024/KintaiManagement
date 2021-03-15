package com.akeng.kintaimanagement.repository;

import com.akeng.kintaimanagement.auth.LoginUser;
import com.akeng.kintaimanagement.model.DailyRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RosterRepository extends JpaRepository<DailyRoster, Long> {
    List<DailyRoster> findAllByUserId(long userId);
    Optional<DailyRoster> findByPunchDateAndUserId(LocalDate today, long userId);
}
