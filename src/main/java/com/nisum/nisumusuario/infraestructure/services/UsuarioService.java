package com.nisum.nisumusuario.infraestructure.services;

import com.nisum.nisumusuario.api.UsuarioApiDelegate;
import com.nisum.nisumusuario.api.mappers.UsuarioMapper;
import com.nisum.nisumusuario.api.model.UsuarioDTO;
import com.nisum.nisumusuario.api.model.UsuarioResponseDTO;
import com.nisum.nisumusuario.domain.entities.Usuario;
import com.nisum.nisumusuario.domain.repositories.UsuarioRepository;
import com.nisum.nisumusuario.utils.constants.Constants;
import com.nisum.nisumusuario.utils.exceptions.ConflictException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioApiDelegate {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final JwtTokenService jwtTokenService;

    @Override
    @CircuitBreaker(name="usuarioPost", fallbackMethod = "fallBackUsuarioPost")
    public ResponseEntity<UsuarioResponseDTO> usuarioPost(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if (usuarioBuscado.isPresent()) {
            throw new ConflictException(Constants.ERROR_MESSAGE_CONFLICT_EMAIL);
        }

        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper
                .usuarioToUsuarioResponseDTO(usuario);
        String token = jwtTokenService.generateToken(usuario.getEmail());
        usuarioResponseDTO.setToken(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    public ResponseEntity<UsuarioResponseDTO> fallBackUsuarioPost(UsuarioDTO usuarioDTO, PersistenceException ex) {
        throw new RuntimeException(Constants.ERROR_MESSAGE_BD);
    }
}
