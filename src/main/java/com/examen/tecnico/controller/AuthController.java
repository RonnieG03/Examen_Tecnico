package com.examen.tecnico.controller;

import com.examen.tecnico.dto.AuthResponseDto;
import com.examen.tecnico.dto.LoginDto;
import com.examen.tecnico.dto.RegisterDto;
import com.examen.tecnico.model.UserEntity;
import com.examen.tecnico.security.JWTGenerator;
import com.examen.tecnico.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.examen.tecnico.enums.RoleUser.ROLE_USER;
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        LOGGER.info("User logging in");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        LOGGER.info("New user registration");
        if (userService.existsByUsername(registerDto.getUsername())){
           return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(ROLE_USER);

        userService.save(user);

        return ResponseEntity.ok("User registered success");
    }
}
