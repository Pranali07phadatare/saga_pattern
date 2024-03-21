package com.neosoft.orderService.Repository;

import com.neosoft.orderService.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}
