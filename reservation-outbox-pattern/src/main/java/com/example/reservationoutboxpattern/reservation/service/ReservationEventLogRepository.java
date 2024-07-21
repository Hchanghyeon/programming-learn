package com.example.reservationoutboxpattern.reservation.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservationoutboxpattern.reservation.domain.ReservationStatus;

public interface ReservationEventLogRepository extends JpaRepository<ReservationEventLog, Long> {

     List<ReservationEventLog> findByStatus(final ReservationStatus reservationStatus);
}
