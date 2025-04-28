package org.e2e.labe2e01.driver.domain;

import org.e2e.labe2e01.user.domain.Role;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DriverTest {
    private Driver driver;

    private Vehicle vehicle;

    public void setUpVehicle() {
        vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setLicensePlate("ABC123");
        vehicle.setFabricationYear(2020);
        vehicle.setCapacity(5);
    }

    public void setUpDriver() {
        driver = new Driver();
        driver.setRole(Role.DRIVER);
        driver.setFirstName("Juan");
        driver.setLastName("Perez");
        driver.setEmail("juanperez@gmail.com");
        driver.setPassword("123456");
        driver.setPhoneNumber("987654321");

        driver.setCategory(Category.X);
        driver.setVehicle(vehicle);
    }

    @BeforeEach
    void setUp() {
        setUpVehicle();
        setUpDriver();
    }

    @Test
    void testDriverCreation() {
        Assertions.assertNotNull(driver);
        Assertions.assertEquals(Role.DRIVER, driver.getRole());
        Assertions.assertEquals("Juan", driver.getFirstName());
        Assertions.assertEquals("Perez", driver.getLastName());
        Assertions.assertEquals("juanperez@gmail.com", driver.getEmail());
        Assertions.assertEquals("123456", driver.getPassword());
        Assertions.assertEquals("987654321", driver.getPhoneNumber());
        Assertions.assertEquals(Category.X, driver.getCategory());
        Assertions.assertEquals(vehicle, driver.getVehicle());
    }

    @Test
    void testDriverCategory() {
        Assertions.assertEquals(Category.X, driver.getCategory());
        driver.setCategory(Category.XL);
        Assertions.assertEquals(Category.XL, driver.getCategory());
    }

    @Test
    void testGetVehicle() {
        Assertions.assertEquals(vehicle, driver.getVehicle());
        Assertions.assertEquals("Toyota", driver.getVehicle().getBrand());
    }


    @Test
    void testDriverNull() {
        driver = null;
        assertNull(driver);
    }
}
