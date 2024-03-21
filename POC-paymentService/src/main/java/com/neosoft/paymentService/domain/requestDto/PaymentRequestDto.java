package com.neosoft.paymentService.domain.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentRequestDto {
    private UUID orderId;

    private UUID userId;

    private BigDecimal amount;

}
