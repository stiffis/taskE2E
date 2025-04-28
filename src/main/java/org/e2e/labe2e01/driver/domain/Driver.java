package org.e2e.labe2e01.driver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.user.domain.User;
import org.e2e.labe2e01.vehicle.domain.Vehicle;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Driver extends User {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "SMALLINT")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "vehicle", nullable = false, columnDefinition = "BIGINT")
    private Vehicle vehicle;
}
