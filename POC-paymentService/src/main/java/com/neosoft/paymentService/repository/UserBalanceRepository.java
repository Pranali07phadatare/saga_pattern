package com.neosoft.paymentService.repository;

import com.neosoft.paymentService.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBalanceRepository extends JpaRepository<UserBalance, UUID> {
}
