package com.mcheaman.TeamViewerTechnical.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="order-items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;
    private Long orderId;
    private Long productId;
}