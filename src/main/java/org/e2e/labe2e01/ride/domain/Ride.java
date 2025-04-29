package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@Data
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Double price;

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

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String destinationName;

}
