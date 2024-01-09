package com.mcheaman.TeamViewerTechnical.repositories;

import com.mcheaman.TeamViewerTechnical.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
