package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.dto.JwtRequestDto;
import br.com.codewave.codewave.dto.JwtResponseDto;
import br.com.codewave.codewave.services.usuarioSecurity.JwtTokenService;
import br.com.codewave.codewave.services.usuarioSecurity.UsuarioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class LoginController {
    @Autowired
    private UsuarioSecurity usuarioSecurity;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) throws Exception {

        if (!usuarioSecurity.userExist("admin")) {
            usuarioSecurity.createAdminUser();
        }
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = usuarioSecurity.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenService.generateToken(userDetails);
        return new ResponseEntity(new JwtResponseDto(token), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}