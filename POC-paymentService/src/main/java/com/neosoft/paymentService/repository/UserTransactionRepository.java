package com.neosoft.paymentService.repository;

import com.neosoft.paymentService.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, UUID> {
}
