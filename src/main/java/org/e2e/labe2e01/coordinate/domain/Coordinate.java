package org.e2e.labe2e01.coordinate.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.userLocations.domain.UserLocation;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, columnDefinition = "DOUBLE PRECISION")
    private double latitude;

    @Column(nullable = false, columnDefinition = "DOUBLE PRECISION")
    private double longitude;

    @OneToMany(mappedBy = "coordinate", orphanRemoval = true)
    private List<UserLocation> passengers = new ArrayList<>();

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
