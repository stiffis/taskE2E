package org.e2e.labe2e01.driver.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.domain.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    // Crear un nuevo conductor
    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver createdDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    // Obtener todos los conductores
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    // Obtener un conductor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Actualizar un conductor
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driverDetails) {
        try {
            Driver updatedDriver = driverService.updateDriver(id, driverDetails);
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar un conductor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar ubicación del conductor
    @PatchMapping("/{id}/location")
    public ResponseEntity<Driver> updateLocation(@PathVariable Long id, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            // Aquí agregarías la lógica de actualización de la ubicación si fuera necesario
            Driver updatedDriver = driver.get();
            // Ejemplo de actualización de ubicación
            // updatedDriver.getLocation().setLatitude(latitude);
            // updatedDriver.getLocation().setLongitude(longitude);
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Asignar vehículo al conductor
    @PatchMapping("/{id}/car")
    public ResponseEntity<Driver> updateCar(@PathVariable Long id, @RequestParam("vehicleId") Long vehicleId) {
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            // Aquí asignarías el vehículo
            Driver updatedDriver = driver.get();
            // updatedDriver.setVehicle(new Vehicle(vehicleId)); // Lógica para asignar el vehículo
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
