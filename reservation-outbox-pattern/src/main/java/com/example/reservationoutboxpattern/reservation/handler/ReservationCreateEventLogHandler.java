package com.example.reservationoutboxpattern.reservation.handler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.reservationoutboxpattern.reservation.service.ReservationEventLog;
import com.example.reservationoutboxpattern.reservation.service.ReservationEventLogRepository;
import com.example.reservationoutboxpattern.reservation.service.ReservationEventMessage;
import com.example.reservationoutboxpattern.reservation.service.ReservationEventMessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationCreateEventLogHandler {

    private final ReservationEventLogRepository reservationEventLogRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void saveEventLog(final ReservationEventMessage reservationEventMessage){
        if(reservationEventMessage.type() == ReservationEventMessageType.RERTY){
            return;
        }

        reservationEventLogRepository.save(
                new ReservationEventLog(
                        reservationEventMessage.hotelId(),
                        reservationEventMessage.memberId(),
                        reservationEventMessage.hotelNumber(),
                        LocalDateTime.now()
                )
        );

        log.info("log save success");
    }
}
