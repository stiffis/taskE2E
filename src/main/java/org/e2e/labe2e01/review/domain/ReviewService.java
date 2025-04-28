package org.e2e.labe2e01.review.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.e2e.labe2e01.review.infrastructure.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // Guardar una nueva revisión
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Obtener todas las revisiones
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Obtener una revisión por su ID
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    // Actualizar una revisión
    public Review updateReview(Long id, Review reviewDetails) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setRating(reviewDetails.getRating());
            review.setComment(reviewDetails.getComment());
            review.setAuthor(reviewDetails.getAuthor());
            review.setTarget(reviewDetails.getTarget());
            review.setRide(reviewDetails.getRide());
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Revisión no encontrada");
        }
    }

    // Eliminar una revisión
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    // Obtener todas las revisiones de un pasajero o conductor
    public List<Review> getReviewsByTarget(Long targetId) {
        return reviewRepository.findByTargetId(targetId);
    }

    // Obtener todas las revisiones de una determinada carrera
    public List<Review> getReviewsByRide(Long rideId) {
        return reviewRepository.findByRideId(rideId);
    }
}
