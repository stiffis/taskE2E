package org.e2e.labe2e01.driver.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    public Driver updateDriver(Long id, Driver driverDetails) {
        Optional<Driver> existingDriver = driverRepository.findById(id);
        if (existingDriver.isPresent()) {
            Driver driver = existingDriver.get();
            driver.setCategory(driverDetails.getCategory());
            driver.setVehicle(driverDetails.getVehicle());
            driver.setLatitude(driverDetails.getLatitude());
            driver.setLongitude(driverDetails.getLongitude());
            return driverRepository.save(driver);
        } else {
            throw new RuntimeException("Conductor no encontrado");
        }
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
