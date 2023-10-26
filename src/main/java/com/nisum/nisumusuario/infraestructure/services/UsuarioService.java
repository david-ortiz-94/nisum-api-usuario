package com.nisum.nisumusuario.infraestructure.services;

import com.nisum.nisumusuario.api.UsuarioApiDelegate;
import com.nisum.nisumusuario.api.model.UsuarioDTO;
import com.nisum.nisumusuario.api.model.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioApiDelegate {

    @Override
    public ResponseEntity<UsuarioResponseDTO> usuarioPost(UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(UsuarioResponseDTO.builder()
                .email(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .name(usuarioDTO.getName())
                .phones(usuarioDTO.getPhones())
                .build());
    }
}
