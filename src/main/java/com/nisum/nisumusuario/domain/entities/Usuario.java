package com.nisum.nisumusuario.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefono> phones = new ArrayList<>();
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
        lastLogin = LocalDateTime.now();
        isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        modified = LocalDateTime.now();
    }
}
