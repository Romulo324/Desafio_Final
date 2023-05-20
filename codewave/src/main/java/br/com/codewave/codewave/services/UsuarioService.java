package br.com.codewave.Models.src.main.java.br.com.codewave.Models.services;

import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Usuario;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
    @Autowired
    public UsuarioRepository usuarioRepository;

    public void adicionar(Usuario usuarioQueSeraSalvo) {
        usuarioRepository.save(usuarioQueSeraSalvo);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario acharPorId(Integer id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()){
            throw new RuntimeException("Usuario Não Encontrado");
        }
        return optionalUsuario.get();
    }

    public void atualizar(Integer id, Usuario usuario){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.save(usuario);
        }
    }
    public void remove(Integer id) {
        Usuario pesquisarUsuario = acharPorId(id);
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }
    }
}
