package com.neosoft.orderService;

import com.neosoft.orderService.domain.enums.OrderStatus;
import com.neosoft.orderService.domain.requestDto.OrderRequestDto;
import com.neosoft.orderService.model.PurchaseOrder;

import java.util.UUID;

public class helper {
    public helper() {
    }

    public static PurchaseOrder buildPurchaseOrder(OrderRequestDto orderRequestDto) {

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .id(UUID.randomUUID())
                .userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .price(orderRequestDto.getAmount())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .paymentStatus(null)
                .build();

        return purchaseOrder;
    }
}
