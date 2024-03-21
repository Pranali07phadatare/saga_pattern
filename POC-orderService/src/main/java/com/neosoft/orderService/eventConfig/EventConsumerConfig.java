package com.neosoft.orderService.eventConfig;


import com.neosoft.orderService.domain.requestDto.PaymentEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {

    @Autowired
    private OrderStatusUpdateHandler handler;


    @Bean
    public Consumer<PaymentEventDto> paymentEventConsumer(){
        //listen payment-event-topic
        //will check payment status
        //if payment status completed -> complete the order
        //if payment status failed -> cancel the order
        return (payment)-> handler.updateOrder(payment.getPaymentRequestDto().getOrderId(),po->{
            po.setPaymentStatus(payment.getPaymentStatus());
        });
    }
}