package org.e2e.labe2e01.review.domain;

import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.user.domain.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class ReviewTest {
    private Review review;

    private Ride ride;

    private Passenger user;

    void setUpPassenger() {
        user = new Passenger();
        user.setRole(Role.PASSENGER);
        user.setFirstName("Carlos");
        user.setLastName("Gutierrez");
        user.setEmail("carlosgutierrez@gmail.com");
        user.setPassword("123456");
        user.setPhoneNumber("987654321");
        user.setCreatedAt(ZonedDateTime.now());

        ride = new Ride();
    }

    @BeforeEach
    void setUp() {
        setUpPassenger();
        review = new Review();
        review.setComment("comment1");
        review.setRating(5);
        review.setAuthor(user);
        review.setTarget(user);
        review.setRide(ride);
    }

    @Test
    void testReviewCreation() {
        Assertions.assertEquals("comment1", review.getComment());
        Assertions.assertEquals(5, review.getRating());
        Assertions.assertEquals(user, review.getAuthor());
        Assertions.assertEquals(user, review.getTarget());
        Assertions.assertEquals(ride, review.getRide());
    }
}
