package com.neosoft.orderService.domain.requestDto;

import com.neosoft.orderService.domain.enums.OrderStatus;
import com.neosoft.orderService.domain.requestDto.OrderRequestDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Data
public class OrderEventDto {

    private  OrderStatus orderStatus;

    private  OrderRequestDto orderRequestDto;

    private LocalDateTime date;

    private UUID eventId= UUID.randomUUID();
}
