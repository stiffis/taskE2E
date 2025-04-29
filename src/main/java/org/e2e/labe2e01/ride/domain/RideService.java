package org.e2e.labe2e01.ride.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e01.ride.infrastructure.RideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;

    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public Ride assignDriverToRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId).orElse(null);
        if (ride != null) {
            Driver driver = driverRepository.findById(driverId).orElse(null);  // Inicializamos el conductor
            if (driver != null) {
                ride.setDriver(driver);
                return rideRepository.save(ride);
            }
        }
        return null;
    }

    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }

    public Page<Ride> getRidesByPassenger(Long passengerId, PageRequest pageRequest) {
        return rideRepository.findByPassengerId(passengerId, pageRequest);  // Debe ser implementado en el repositorio
    }

    public Ride updateRide(Long id, Ride rideDetails) {
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride != null) {
            // Actualizar detalles del viaje
            ride.setPrice(rideDetails.getPrice());
            ride.setStatus(rideDetails.getStatus());
            ride.setArrivalDate(rideDetails.getArrivalDate());
            ride.setDepartureDate(rideDetails.getDepartureDate());
            ride.setDestinationCoordinates(rideDetails.getDestinationCoordinates());
            ride.setOriginCoordinates(rideDetails.getOriginCoordinates());
            ride.setPassenger(rideDetails.getPassenger());
            ride.setOriginName(rideDetails.getOriginName());
            ride.setDestinationName(rideDetails.getDestinationName());
            return rideRepository.save(ride);
        }

        return null;
    }

    public Ride updateRideStatus(Long id, Status newStatus) {
        Ride ride = rideRepository.findById(id).orElseThrow();
        ride.setStatus(newStatus);
        return rideRepository.save(ride);
    }
}
