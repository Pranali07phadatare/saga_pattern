package com.neosoft.orderService.domain.responseDto;

import com.neosoft.orderService.domain.enums.OrderStatus;
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
public class OrderResponseDto {

    private UUID userId;

    private UUID productId;

    private UUID orderId;

    private OrderStatus orderStatus;

    private BigDecimal amount;
}
