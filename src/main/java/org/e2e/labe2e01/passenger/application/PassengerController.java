package org.e2e.labe2e01.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.domain.PassengerService;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    // Obtener un pasajero por ID
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        return passenger.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un pasajero por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar un pasajero (Descripción de la ubicación)
    @PatchMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long id, @RequestParam String description, @RequestBody Coordinate coordinate) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        if (passenger.isPresent()) {
            passenger.get().addPlace(coordinate, description);
            passengerService.save(passenger.get());
            return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Obtener las ubicaciones de un pasajero
    @GetMapping("/{id}/places")
    public ResponseEntity<List<Coordinate>> getPassengerPlaces(@PathVariable Long id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        if (passenger.isPresent()) {
            List<Coordinate> places = passenger.get().getPlacesList();
            return new ResponseEntity<>(places, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar una ubicación específica de un pasajero
    @DeleteMapping("/{id}/places/{coordinateId}")
    public ResponseEntity<Void> deletePassengerPlace(@PathVariable Long id, @PathVariable Long coordinateId) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        if (passenger.isPresent()) {
            passenger.get().removePlace(coordinateId);
            passengerService.save(passenger.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
