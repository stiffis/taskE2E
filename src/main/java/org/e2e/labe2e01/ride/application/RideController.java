package org.e2e.labe2e01.ride.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.RideService;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    // Crear un nuevo viaje
    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        Ride savedRide = rideService.saveRide(ride);
        return new ResponseEntity<>(savedRide, HttpStatus.CREATED);
    }

    // Asignar un conductor a un viaje
    @PatchMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<Ride> assignDriver(@PathVariable Long rideId, @PathVariable Long driverId) {
        Ride ride = rideService.assignDriverToRide(rideId, driverId);
        if (ride != null) {
            return new ResponseEntity<>(ride, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un viaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener los viajes de un pasajero con paginación
    @GetMapping("/{passengerId}")
    public ResponseEntity<Page<Ride>> getRidesByPassenger(
            @PathVariable Long passengerId,
            @RequestParam int page,
            @RequestParam int size) {

        Page<Ride> rides = rideService.getRidesByPassenger(passengerId, PageRequest.of(page, size));
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    // Actualizar un viaje
    @PatchMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id, @RequestBody Ride rideDetails) {
        Ride updatedRide = rideService.updateRide(id, rideDetails);
        if (updatedRide != null) {
            return new ResponseEntity<>(updatedRide, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
