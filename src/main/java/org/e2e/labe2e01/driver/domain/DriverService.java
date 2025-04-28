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

    // Crear un nuevo conductor
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // Obtener todos los conductores
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Obtener un conductor por su ID
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    // Actualizar un conductor
    public Driver updateDriver(Long id, Driver driverDetails) {
        Optional<Driver> existingDriver = driverRepository.findById(id);
        if (existingDriver.isPresent()) {
            Driver driver = existingDriver.get();
            driver.setCategory(driverDetails.getCategory());
            driver.setVehicle(driverDetails.getVehicle());
            // Aquí puedes agregar más campos a actualizar si es necesario
            return driverRepository.save(driver);
        } else {
            throw new RuntimeException("Conductor no encontrado");
        }
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
