package br.com.marco.springApi.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository1 extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
