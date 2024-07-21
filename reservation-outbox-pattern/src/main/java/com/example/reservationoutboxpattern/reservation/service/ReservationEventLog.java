package com.example.reservationoutboxpattern.reservation.service;

import java.time.LocalDateTime;

import com.example.reservationoutboxpattern.reservation.domain.ReservationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long hotelId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long hotelRoomNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public ReservationEventLog(
            final Long hotelId,
            final Long memberId,
            final Long hotelRoomNumber,
            final LocalDateTime createdAt
    ) {
        this.hotelId = hotelId;
        this.memberId = memberId;
        this.status = ReservationStatus.FAILED;
        this.hotelRoomNumber = hotelRoomNumber;
        this.createdAt = createdAt;
    }
}
