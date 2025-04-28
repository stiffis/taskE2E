package org.e2e.labe2e01.passenger.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e01.user.domain.Role;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PassengerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Passenger passenger;

    private Coordinate coordinate;

    @BeforeEach
    public void setUp() {
        passenger = new Passenger();
        passenger.setFirstName("Alice");
        passenger.setId(1L);
        passenger.setFirstName("Jane");
        passenger.setLastName("Doe");
        passenger.setEmail("example@email.com");
        passenger.setPassword("password");
        passenger.setPhoneNumber("0987654321");
        passenger.setCreatedAt(ZonedDateTime.now());
        passenger.setRole(Role.PASSENGER);

        coordinate = new Coordinate();
        coordinate.setId(1L);
        coordinate.setLatitude(42.1234);
        coordinate.setLongitude(-71.9876);
    }

    @Test
    public void testGetPassengerById() throws Exception {
        Passenger currentPassenger = passengerRepository.save(passenger);

        mockMvc.perform(MockMvcRequestBuilders.get("/passenger/{id}", currentPassenger.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(currentPassenger.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(currentPassenger.getFirstName())));

    }

    @Test
    public void testDeletePassenger() throws Exception {
        Passenger currentPassenger = passengerRepository.save(passenger);

        mockMvc.perform(delete("/passenger/{id}", currentPassenger.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertFalse(passengerRepository.findById(currentPassenger.getId()).isPresent());
    }

    @Test
    public void testAddPlace() throws Exception {
        Passenger currentPassenger = passengerRepository.save(passenger);
        Coordinate currentCoordinate = coordinateRepository.save(coordinate);
        currentPassenger.addPlace(currentCoordinate, "Sample Place");
        passengerRepository.save(currentPassenger);
        mockMvc.perform(patch("/passenger/{id}", currentPassenger.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currentPassenger)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeletePlace() throws Exception {
        Passenger currentPassenger = passengerRepository.save(passenger);
        Coordinate currentCoordinate = coordinateRepository.save(coordinate);
        currentPassenger.addPlace(currentCoordinate, "Sample Place");

        mockMvc.perform(delete("/passenger/{id}/places/{coordinateId}", currentPassenger.getId(), currentCoordinate.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Passenger updatedPassenger = passengerRepository.findById(currentPassenger.getId()).orElseThrow();
        Assertions.assertTrue(updatedPassenger.getPlaces().isEmpty());
    }

    @Test
    public void testGetPlaces() throws Exception {
        Passenger currentPassenger = passengerRepository.save(passenger);
        Coordinate currentCoordinate = coordinateRepository.save(coordinate);

        Coordinate coordinate2 = new Coordinate();
        coordinate2.setLatitude(432.1234);
        coordinate2.setLongitude(-731.9876);
        coordinate2 = coordinateRepository.save(coordinate2);

        currentPassenger.addPlace(currentCoordinate, "Place 1");
        currentPassenger.addPlace(coordinate2, "Place 2");

        passengerRepository.save(currentPassenger);

        mockMvc.perform(MockMvcRequestBuilders.get("/passenger/{id}/places", currentPassenger.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }
}
