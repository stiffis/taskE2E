package org.e2e.labe2e01.userLocations.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;

@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data
public class UserLocation {

    @EmbeddedId
    private PassengerCoordinateId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("passengerId")
    @JoinColumn(name = "passenger", referencedColumnName = "id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("coordinateId")
    @JoinColumn(name = "coordinate", referencedColumnName = "id", nullable = false)
    private Coordinate coordinate;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;

    public UserLocation(Passenger passenger, Coordinate coordinate, String description) {
        this.passenger = passenger;
        this.coordinate = coordinate;
        this.description = description;
        this.id = new PassengerCoordinateId(passenger.getId(), coordinate.getId());  // Ahora resuelto
    }
}
