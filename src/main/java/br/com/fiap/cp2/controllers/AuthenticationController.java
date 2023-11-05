package br.com.fiap.cp2.controllers;

import br.com.fiap.cp2.dtos.LoginDto;
import br.com.fiap.cp2.dtos.TokenJWT;
import br.com.fiap.cp2.models.UserModel;
import br.com.fiap.cp2.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDto loginDto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UserModel) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));

    }

}
