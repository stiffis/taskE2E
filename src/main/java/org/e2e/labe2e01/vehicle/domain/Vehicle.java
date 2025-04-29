package org.e2e.labe2e01.vehicle.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer fabricationYear;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private String model;

}
