package com.neosoft.paymentService.domain.requestDto;


import com.neosoft.paymentService.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Data
public class OrderEventDto {

    private OrderStatus orderStatus;

    private  OrderRequestDto orderRequestDto;

    private LocalDateTime date;

    private UUID eventId= UUID.randomUUID();
}
