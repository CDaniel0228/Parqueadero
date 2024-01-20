package com.example.demo.Control;
import com.example.demo.Modelo.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    //List<Usuario> findByTitleContaining(String nombre);
}
