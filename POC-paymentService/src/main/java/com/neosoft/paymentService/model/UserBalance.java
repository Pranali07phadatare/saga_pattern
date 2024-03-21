package com.neosoft.paymentService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Table(name = "user_balance")
public class UserBalance {
    @Id
    private UUID userId;
    private BigDecimal price;
}
