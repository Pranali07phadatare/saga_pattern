package com.neosoft.orderService.event;

import com.neosoft.orderService.domain.enums.OrderStatus;
import com.neosoft.orderService.domain.requestDto.OrderEventDto;
import com.neosoft.orderService.domain.requestDto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderEventPublisher {

    @Autowired
    private Sinks.Many<OrderEventDto> orderEventDtoMany;

    public void publishOredrEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
        OrderEventDto orderEventDto = OrderEventDto.builder()
                .orderRequestDto(orderRequestDto)
                .orderStatus(orderStatus)
                .build();
        orderEventDtoMany.tryEmitNext(orderEventDto);
    }


}
