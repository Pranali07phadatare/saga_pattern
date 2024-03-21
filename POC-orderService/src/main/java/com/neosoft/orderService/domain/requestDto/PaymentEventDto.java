package com.neosoft.orderService.domain.requestDto;


import com.neosoft.orderService.domain.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Data
public class PaymentEventDto {

    private LocalDateTime eventDate;

    private UUID eventId= UUID.randomUUID();

    private PaymentRequestDto paymentRequestDto;

    private PaymentStatus paymentStatus;

    public PaymentEventDto(PaymentRequestDto paymentRequestDto, PaymentStatus paymentCompleted) {
    }
}
