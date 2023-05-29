package br.com.codewave.codewave.securityService;

import br.com.codewave.codewave.repositories.securityRepository.UsuarioRepositorySecurity;
import br.com.codewave.codewave.usuarioSecurity.UsuarioSecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorySecurity usuarioRepositorySecurity;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositorySecurity.findByUsername(username);
    }

    public boolean userExist(String username) {
        UsuarioSecurityModel usuarioSecurityModel = usuarioRepositorySecurity.findByUsername(username);
        return (usuarioSecurityModel != null);
    }

    public void createAdminUser() {
        UsuarioSecurityModel userAdmin = new UsuarioSecurityModel();
        userAdmin.setUsername("admin");
        userAdmin.setPassword(passwordEncoder.encode("123"));
        userAdmin.setNomeCompleto("Adminstrador");

        usuarioRepositorySecurity.save(userAdmin);
    }
}
