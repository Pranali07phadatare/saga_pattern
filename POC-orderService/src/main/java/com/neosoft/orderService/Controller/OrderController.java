package com.neosoft.orderService.Controller;

import com.neosoft.orderService.domain.requestDto.OrderRequestDto;
import com.neosoft.orderService.model.PurchaseOrder;
import com.neosoft.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return  orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/allOrders")
    public List<PurchaseOrder> getAllOrders(){
        return  orderService.getAllOrders();
    }
}
