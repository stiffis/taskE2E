package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL")
    private Long id;

    @Column(nullable = false, columnDefinition = "DOUBLE PRECISION")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column
    private ZonedDateTime arrivalDate;

    @Column
    private ZonedDateTime departureDate;

    @ManyToOne
    @JoinColumn(name = "destination_coordinates_id", referencedColumnName = "id", nullable = false)
    private Coordinate destinationCoordinates;

    @ManyToOne
    @JoinColumn(name = "origin_coordinates_id", referencedColumnName = "id", nullable = false)
    private Coordinate originCoordinates;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", nullable = false)
    private Passenger passenger;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String originName;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String destinationName;
}
