package com.neosoft.paymentService.config;


import com.neosoft.paymentService.domain.enums.OrderStatus;
import com.neosoft.paymentService.domain.requestDto.OrderEventDto;
import com.neosoft.paymentService.domain.requestDto.PaymentEventDto;
import com.neosoft.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEventDto>, Flux<PaymentEventDto>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEventDto> processPayment(OrderEventDto orderEvent) {
        // get the user id
        // check the balance availability
        // if balance sufficient -> Payment completed and deduct amount from DB
        // if payment not sufficient -> cancel order event and update the amount in DB
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return  Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
        }else{
            return Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}