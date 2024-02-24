package com.example.otpgen.repository;

import com.example.otpgen.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP,Long> {
    Optional<OTP> findByIdEquals(Long id);
    List<OTP> deleteAllByTimestampBefore(LocalTime time);
}
