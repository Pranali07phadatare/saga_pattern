package com.neosoft.orderService.domain.requestDto;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private UUID userId;

    private UUID productId;

    private UUID orderId;

    private BigDecimal amount;

}
