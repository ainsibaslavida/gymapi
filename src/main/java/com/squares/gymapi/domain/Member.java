package com.squares.gymapi.domain;

import com.squares.gymapi.domain.types.Plans;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Plans plan;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(nullable = false)
    private Boolean active;
}
