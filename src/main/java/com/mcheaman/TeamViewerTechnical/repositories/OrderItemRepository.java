package com.mcheaman.TeamViewerTechnical.repositories;

import com.mcheaman.TeamViewerTechnical.models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {
}
