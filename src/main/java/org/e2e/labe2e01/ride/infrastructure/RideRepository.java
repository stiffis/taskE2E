package org.e2e.labe2e01.ride.infrastructure;

import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride,Long> {
    Page<Ride> findByPassengerId(Long passengerId, PageRequest pageRequest);

    Page<Ride> findAllByPassengerIdAndStatus(Long id, Status status, PageRequest of);
}
