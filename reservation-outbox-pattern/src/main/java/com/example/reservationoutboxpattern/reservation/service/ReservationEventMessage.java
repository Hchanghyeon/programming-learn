package com.example.reservationoutboxpattern.reservation.service;

public record ReservationEventMessage(Long hotelId, Long hotelNumber, Long memberId, String memberEmail, ReservationEventMessageType type) {

}
