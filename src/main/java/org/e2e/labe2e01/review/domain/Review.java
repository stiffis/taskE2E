package org.e2e.labe2e01.review.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.user.domain.User;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "ride_id", referencedColumnName = "id", nullable = false)
    private Ride ride;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "target_id", referencedColumnName = "id", nullable = false)
    private User target;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;
}
