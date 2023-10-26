package com.nisum.nisumusuario.infraestructure.services;

import com.nisum.nisumusuario.api.UsuarioApiDelegate;
import com.nisum.nisumusuario.api.mappers.UsuarioMapper;
import com.nisum.nisumusuario.api.model.UsuarioDTO;
import com.nisum.nisumusuario.api.model.UsuarioResponseDTO;
import com.nisum.nisumusuario.domain.entities.Usuario;
import com.nisum.nisumusuario.domain.repositories.UsuarioRepository;
import com.nisum.nisumusuario.utils.constants.Constants;
import com.nisum.nisumusuario.utils.exceptions.ConflictException;
import lombok.AllArgsConstructor;
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
    @Override
    public ResponseEntity<UsuarioResponseDTO> usuarioPost(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(usuarioDTO.getEmail());

        if (usuarioBuscado.isPresent()) {
            throw new ConflictException(Constants.ERROR_MESSAGE_CONFLICT_EMAIL);
        }

        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioMapper.usuarioToUsuarioResponseDTO(usuario));
    }
}
