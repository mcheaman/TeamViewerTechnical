package com.mcheaman.TeamViewerTechnical.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long orderId;
    private Long productId;
}
