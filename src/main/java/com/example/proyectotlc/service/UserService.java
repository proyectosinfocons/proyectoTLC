package com.example.proyectotlc.service;




import com.example.proyectotlc.common.EntityDtoConverter;
import com.example.proyectotlc.common.UserValidator;
import com.example.proyectotlc.dto.LoginRequest;
import com.example.proyectotlc.dto.LoginResponse;
import com.example.proyectotlc.dto.User;
import com.example.proyectotlc.exception.IncorrectOrderRequestException;
import com.example.proyectotlc.exception.OrderNotFoundException;
import com.example.proyectotlc.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Value("${jwt.password}")
    private String jwtSecret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        try {
            UserValidator.validate(user);
            User existUser=userRepository.findByUsername(user.getUsername()).orElse(null);
            if(existUser!=null)
                throw new IncorrectOrderRequestException("El nombre usuario ya existe");

            String encoder=passwordEncoder.encode(user.getPassword());
            user.setPassword(encoder);

            return userRepository.save(user);
        } catch (IncorrectOrderRequestException | OrderNotFoundException e) {
            throw e;
        }
    }
    

    public LoginResponse login(LoginRequest request){
        try {
            User user=userRepository.findByUsername(request.getUsername())
                    .orElseThrow(()->new IncorrectOrderRequestException("Usuario o password incorrecto"));

            if(!passwordEncoder.matches(request.getPassword(),user.getPassword()))
                throw new IncorrectOrderRequestException("Usuario o password incorrectos");

            String token =createToken(user);

            return new LoginResponse(entityDtoConverter.convertEntityToDto(user),token);

        } catch (IncorrectOrderRequestException | OrderNotFoundException e) {
            throw e;
        } 
    }

    //Fuente: https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt
    public String createToken(User user){
        Date now =new Date();
        Date expiryDate=new Date(now.getTime()+ (1000*60*60));

        return Jwts.builder()
            .setSubject(user.getUsername())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException e) {
            log.error("JWT in a particular format/configuration that does not match the format expected");
        }catch (MalformedJwtException e) {
            log.error(" JWT was not correctly constructed and should be rejected");
        }catch (SignatureException e) {
            log.error("Signature or verifying an existing signature of a JWT failed");
        }catch (ExpiredJwtException e) {
            log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            throw new IncorrectOrderRequestException("Invalid Token");
        }

    }


}