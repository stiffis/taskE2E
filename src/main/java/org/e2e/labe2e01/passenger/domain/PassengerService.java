package org.e2e.labe2e01.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    // Método para obtener un pasajero por su ID
    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    // Método para eliminar un pasajero por su ID
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    // Método para guardar un pasajero
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }
}
