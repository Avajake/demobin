package dev.fox.demobin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductType productType;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "marketplace_id", nullable = false)  // Внешний ключ к таблице Marketplace
    private Marketplace marketplace;

    @Column(nullable = false)
    private LocalDateTime shipmentDate = LocalDateTime.now();
}
