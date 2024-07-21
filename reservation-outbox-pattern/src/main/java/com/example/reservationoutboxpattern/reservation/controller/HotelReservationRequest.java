package com.example.reservationoutboxpattern.reservation.controller;

import com.example.reservationoutboxpattern.reservation.domain.Reservation;

public record HotelReservationRequest(Long id, Long memberId, Long roomNumber) {

    public Reservation toEntity(){
        return new Reservation(memberId, roomNumber);
    }
}
