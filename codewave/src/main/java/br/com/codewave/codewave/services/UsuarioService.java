package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Usuario;
import br.com.codewave.codewave.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
    @Autowired
    public UsuarioRepository usuarioRepository;

    // Método que adiciona um usuario
    public void adicionar(Usuario usuarioQueSeraSalvo) {
        usuarioRepository.save(usuarioQueSeraSalvo);
    }

    // Método que lista todos os usuarios adicionados
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Método que procura um usuario pelo id e lista
    public Usuario acharPorId(Integer id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()){
            throw new RuntimeException("Usuario Não Encontrado");
        }
        return optionalUsuario.get();
    }

    // Método que atualiza um usuario pelo id
    public void atualizar(Integer id, Usuario usuario){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.save(usuario);
        }
    }

    // Método que remove um usuari pelo id
    public void remove(Integer id) {
        Usuario pesquisarUsuario = acharPorId(id);
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }
    }
}
