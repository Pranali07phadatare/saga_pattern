package com.neosoft.orderService.eventConfig;


import com.neosoft.orderService.domain.requestDto.OrderEventDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderEventPublisherConfig {

    @Bean
    public Sinks.Many<OrderEventDto> orderEventDtoMany(){
       return Sinks.many().multicast().onBackpressureBuffer() ;

    }

    @Bean
    public Supplier<Flux<OrderEventDto>> orderSuppliers(Sinks.Many<OrderEventDto> sinks){
        return sinks::asFlux;
    }

}
