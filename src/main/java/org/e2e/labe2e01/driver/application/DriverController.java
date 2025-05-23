
package org.e2e.labe2e01.driver.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.domain.DriverService;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
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

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver createdDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driverDetails) {
        try {
            Driver updatedDriver = driverService.updateDriver(id, driverDetails);
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<Driver> updateLocation(@PathVariable Long id, @RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            Driver updatedDriver = driver.get();
            Double roundedLatitude = Math.round(latitude * 10000.0) / 10000.0;
            Double roundedLongitude = Math.round(longitude * 10000.0) / 10000.0;

            updatedDriver.getCoordinate().setLatitude(roundedLatitude);
            updatedDriver.getCoordinate().setLongitude(roundedLongitude);

            driverService.updateDriver(id, updatedDriver);
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}/car")
    public ResponseEntity<Driver> updateCar(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            Driver updatedDriver = driver.get();
            updatedDriver.setVehicle(vehicle);
            driverService.updateDriver(id, updatedDriver);
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
