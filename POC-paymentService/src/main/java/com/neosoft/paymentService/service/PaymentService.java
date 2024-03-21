package com.neosoft.paymentService.service;

import com.neosoft.paymentService.domain.enums.PaymentStatus;
import com.neosoft.paymentService.domain.requestDto.OrderEventDto;
import com.neosoft.paymentService.domain.requestDto.OrderRequestDto;
import com.neosoft.paymentService.domain.requestDto.PaymentEventDto;
import com.neosoft.paymentService.domain.requestDto.PaymentRequestDto;
import com.neosoft.paymentService.model.UserBalance;
import com.neosoft.paymentService.model.UserTransaction;
import com.neosoft.paymentService.repository.UserBalanceRepository;
import com.neosoft.paymentService.repository.UserTransactionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private  final UserBalanceRepository userBalanceRepository;

    private final UserTransactionRepository userTransactionRepository;


    @PostConstruct
    public void initUserBalanceInDB() {
        userBalanceRepository.saveAll(Stream.of(new UserBalance(UUID.randomUUID(), BigDecimal.valueOf(9000)),
                new UserBalance(UUID.randomUUID(), BigDecimal.valueOf(7000)),
                new UserBalance(UUID.randomUUID(), BigDecimal.valueOf(5000)),
                new UserBalance(UUID.randomUUID(), BigDecimal.valueOf(3000)),
                new UserBalance(UUID.randomUUID(), BigDecimal.valueOf(3500))).collect(Collectors.toList()));
    }

    /**
     * // get the user id
     * // check the balance availability
     * // if balance sufficient -> Payment completed and deduct amount from DB
     * // if payment not sufficient -> cancel order event and update the amount in DB
     **/
    @Transactional
    public PaymentEventDto newOrderEvent(OrderEventDto orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getOrderId(),
                orderRequestDto.getUserId(), orderRequestDto.getAmount());

        return userBalanceRepository.findById(orderRequestDto.getUserId())
                .map(ub -> {
                    if (ub.getPrice().compareTo(orderRequestDto.getAmount()) > 0) {
                        ub.setPrice(ub.getPrice().subtract(orderRequestDto.getAmount()));
                        userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(), orderRequestDto.getUserId(), orderRequestDto.getAmount()));
                        return new PaymentEventDto(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
                    } else {
                        return new PaymentEventDto(paymentRequestDto, PaymentStatus.PAYMENT_FAILED);
                    }
                }).orElse(new PaymentEventDto(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));


    }

    @Transactional
    public void cancelOrderEvent(OrderEventDto orderEvent) {

        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    BigDecimal amountToRevert = ut.getAmount(); // Assuming the amount to revert is stored in the UserTransaction object
                    // Retrieve the user's balance record
                    userBalanceRepository.findById(ut.getUserId())
                            .ifPresent(ub -> {
                                ub.setPrice(ub.getPrice().add(amountToRevert)); // Revert the amount
                                userBalanceRepository.save(ub); // Save the updated balance
                            });
                });

    }
}
