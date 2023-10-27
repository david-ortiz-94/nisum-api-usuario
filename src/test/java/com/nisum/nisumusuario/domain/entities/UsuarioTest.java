package com.nisum.nisumusuario.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsuarioTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void onCreateAndUpdateTest() {
        Usuario usuario = new Usuario();

        assertNull(usuario.getCreated());
        assertNull(usuario.getModified());
        assertNull(usuario.getLastLogin());

        usuario.onCreate();

        LocalDateTime now = LocalDateTime.now();
        assertEquals(now, usuario.getCreated());
        assertEquals(now, usuario.getModified());
        assertEquals(now, usuario.getLastLogin());

        usuario.onUpdate();
    }
}
