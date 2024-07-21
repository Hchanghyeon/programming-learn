package com.example.reservationoutboxpattern.reservation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public void reservationHotel(@RequestBody final HotelReservationRequest hotelReservationRequest){
        reservationService.reservationHotel(hotelReservationRequest);
    }
}
