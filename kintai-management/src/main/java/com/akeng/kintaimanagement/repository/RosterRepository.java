package com.akeng.kintaimanagement.repository;

import com.akeng.kintaimanagement.model.DailyRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RosterRepository extends JpaRepository<DailyRoster, Long> {
    Optional<DailyRoster> findByPunchDate(LocalDate today);
}
