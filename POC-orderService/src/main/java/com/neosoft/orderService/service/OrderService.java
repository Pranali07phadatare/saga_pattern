package com.neosoft.orderService.service;

import com.neosoft.orderService.Repository.OrderRepository;
import com.neosoft.orderService.domain.enums.OrderStatus;
import com.neosoft.orderService.domain.requestDto.OrderRequestDto;
import com.neosoft.orderService.event.OrderEventPublisher;
import com.neosoft.orderService.model.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.neosoft.orderService.helper.buildPurchaseOrder;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        PurchaseOrder purchaseOrder= orderRepository.save(buildPurchaseOrder(orderRequestDto));
        orderRequestDto.setOrderId(purchaseOrder.getId());

//        once the order is created ..publish the order event using kafka
        orderEventPublisher.publishOredrEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return purchaseOrder;

    }

    public List<PurchaseOrder> getAllOrders(){
        return orderRepository.findAll();
    }


}
