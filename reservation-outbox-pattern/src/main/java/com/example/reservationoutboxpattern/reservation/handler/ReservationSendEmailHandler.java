package com.example.reservationoutboxpattern.reservation.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.reservationoutboxpattern.reservation.service.ReservationEventMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationSendEmailHandler {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendEmail(final ReservationEventMessage reservationEventMessage){
        log.info("sendEmail to {}, hotelId:{}, hotelNumber:{}", reservationEventMessage.memberId(), reservationEventMessage.hotelId(), reservationEventMessage.hotelNumber());
        // 필드값 변경
    }
}
