package com.touroperator.infrastructure.adapter.out.persistence.entity;

import com.touroperator.domain.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@Setter
public class UserEntity {
    @Id
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 100
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private boolean active;
}
