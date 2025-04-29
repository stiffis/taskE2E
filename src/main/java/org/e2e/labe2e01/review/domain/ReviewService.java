package org.e2e.labe2e01.review.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.review.infrastructure.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

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

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByTarget(Long targetId) {
        return reviewRepository.findByTargetId(targetId);
    }

    public List<Review> getReviewsByRide(Long rideId) {
        return reviewRepository.findByRideId(rideId);
    }
}
