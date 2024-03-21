package com.neosoft.paymentService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
}

