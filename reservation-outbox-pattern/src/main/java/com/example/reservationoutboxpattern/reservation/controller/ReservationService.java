package com.example.reservationoutboxpattern.reservation.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservationoutboxpattern.member.Member;
import com.example.reservationoutboxpattern.member.MemberRepository;
import com.example.reservationoutboxpattern.reservation.domain.Reservation;
import com.example.reservationoutboxpattern.reservation.domain.ReservationRepository;
import com.example.reservationoutboxpattern.reservation.service.ReservationEventMessage;
import com.example.reservationoutboxpattern.reservation.service.ReservationEventMessageType;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void reservationHotel(final HotelReservationRequest hotelReservationRequest){
        final Member member = memberRepository.findById(hotelReservationRequest.memberId())
                .orElseThrow(() -> new EntityNotFoundException("not found member"));

        final Reservation reservation = hotelReservationRequest.toEntity();

        reservationRepository.save(reservation);

        applicationEventPublisher.publishEvent(
                new ReservationEventMessage(
                    reservation.getId(),
                    reservation.getRoomNumber(),
                        member.getId(),
                        member.getEmail(),
                        ReservationEventMessageType.ALL
                )
        );
    }
}
