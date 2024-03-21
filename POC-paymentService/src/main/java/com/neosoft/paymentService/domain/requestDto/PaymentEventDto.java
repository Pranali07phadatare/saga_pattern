package com.neosoft.paymentService.domain.requestDto;

import com.neosoft.paymentService.domain.enums.PaymentStatus;
import lombok.*;
import org.w3c.dom.events.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Data
public class PaymentEventDto  {

    private LocalDateTime eventDate;

    private UUID eventId= UUID.randomUUID();

    private PaymentRequestDto paymentRequestDto;

    private PaymentStatus paymentStatus;

    public PaymentEventDto(PaymentRequestDto paymentRequestDto, PaymentStatus paymentCompleted) {
    }
}
