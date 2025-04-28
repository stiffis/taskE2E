package org.e2e.labe2e01.vehicle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private int capacity;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private int fabricationYear;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String brand;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)", unique = true)
    private String licensePlate;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String model;
}
