package com.touroperator.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tours")
@Getter
@Setter
@NoArgsConstructor
public class TourEntity {
    @Id
    @SequenceGenerator(
            name = "tour_seq",
            sequenceName = "tour_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    private Long id;

    @Column(name="tour_name", unique=true, nullable=false)
    private String tourName;

    @Column(name = "tour_description")
    private String tourDescription;
    @Column(name="tour_price", nullable=false, precision = 10, scale = 2)
    private BigDecimal tourPrice;
    @Column(name = "duration")
    private String duration;
    @Column(name = "date_tour", nullable=false)
    private LocalDate dateTour;

    @Column(name = "active", nullable=false)
    private boolean active;

    @CreationTimestamp
    @Column(name = "created_at", nullable=false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "update_at", nullable=false)
    private LocalDateTime updatedAt;
}
