package com.example.reservationoutboxpattern.reservation.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservationoutboxpattern.member.Member;
import com.example.reservationoutboxpattern.member.MemberRepository;
import com.example.reservationoutboxpattern.reservation.domain.Reservation;
import com.example.reservationoutboxpattern.reservation.domain.ReservationStatus;
import com.example.reservationoutboxpattern.reservation.handler.ReservationSendEmailHandler;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationFailedEventRetry {

    private final ReservationEventLogRepository reservationEventLogRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final MemberRepository memberRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void retryReservationFailedEvent(){
        final List<ReservationEventLog> reservationEventLogs = reservationEventLogRepository.findByStatus(ReservationStatus.FAILED);

        reservationEventLogs.forEach(reservationEventLog -> {
                    final Member member = memberRepository.findById(reservationEventLog.getMemberId())
                            .orElseThrow(() -> new EntityNotFoundException("not found member"));

                    applicationEventPublisher.publishEvent(
                            new ReservationEventMessage(
                                    reservationEventLog.getHotelId(),
                                    reservationEventLog.getHotelRoomNumber(),
                                    member.getId(),
                                    member.getEmail(),
                                    ReservationEventMessageType.RERTY
                            )
                    );
            }
        );
    }
}
