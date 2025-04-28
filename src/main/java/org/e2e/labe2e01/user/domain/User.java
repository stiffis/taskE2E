package org.e2e.labe2e01.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.coordinate.domain.Coordinate;

import java.time.ZonedDateTime;

@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinate", referencedColumnName = "id", nullable = false)
    private Coordinate coordinate;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, unique = true, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNumber;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @Column(columnDefinition = "DOUBLE DEFAULT 0")
    private double avgRating;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer trips;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
