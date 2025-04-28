package org.e2e.labe2e01.ride.infrastructure;

import org.e2e.labe2e01.ride.domain.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {

    // Método personalizado para obtener los viajes de un pasajero
    Page<Ride> findByPassengerId(Long passengerId, PageRequest pageRequest);
}
