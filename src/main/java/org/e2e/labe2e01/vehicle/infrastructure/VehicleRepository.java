package org.e2e.labe2e01.vehicle.infrastructure;


import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
