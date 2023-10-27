package com.nisum.nisumusuario.infraestructure.services;

import com.nisum.nisumusuario.api.mappers.UsuarioMapper;
import com.nisum.nisumusuario.api.model.UsuarioDTO;
import com.nisum.nisumusuario.api.model.UsuarioResponseDTO;
import com.nisum.nisumusuario.domain.entities.Usuario;
import com.nisum.nisumusuario.domain.repositories.UsuarioRepository;
import com.nisum.nisumusuario.utils.exceptions.ConflictException;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private JwtTokenService jwtTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void usuarioPostTest() {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setName("Juan Rodriguez");
        usuarioDTO.setEmail("juan@rodriguez.org");
        usuarioDTO.setPassword("hunter2");

        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        when(usuarioMapper.usuarioDTOToUsuario(usuarioDTO)).thenReturn(usuario);
        when(usuarioMapper.usuarioToUsuarioResponseDTO(usuario)).thenReturn(new UsuarioResponseDTO());
        when(jwtTokenService.generateToken(anyString())).thenReturn("token");

        ResponseEntity<UsuarioResponseDTO> responseEntity = usuarioService.usuarioPost(usuarioDTO);
        verify(usuarioRepository).save(usuario);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void usuarioPostTestException() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("juan@rodriguez.org");

        Usuario usuarioExistente = new Usuario();
        when(usuarioRepository.findByEmail("juan@rodriguez.org")).thenReturn(Optional.of(usuarioExistente));

        assertThrows(ConflictException.class, () -> usuarioService.usuarioPost(usuarioDTO));
    }

    @Test
    void fallBackUsuarioPostException() {
        PersistenceException throwable = mock(PersistenceException.class);
        assertThrows(RuntimeException.class, () -> usuarioService
                .fallBackUsuarioPost(new UsuarioDTO(), throwable));
    }
}
