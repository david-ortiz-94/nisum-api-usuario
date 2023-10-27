package com.nisum.nisumusuario.api.mappers;

import com.nisum.nisumusuario.api.model.UsuarioDTO;
import com.nisum.nisumusuario.api.model.UsuarioResponseDTO;
import com.nisum.nisumusuario.domain.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UsuarioMapper {
    @Mappings({
            @Mapping( target = "name", source = "name"),
            @Mapping( target = "email", source = "email"),
            @Mapping( target = "password", source = "password")
    })
    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    @Mappings({
            @Mapping( target = "created", source = "created"),
            @Mapping( target = "modified", source = "modified"),
            @Mapping( target = "lastLogin", source = "lastLogin"),
            @Mapping( target = "isactive", source = "isActive")
    })
    UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario);
}
