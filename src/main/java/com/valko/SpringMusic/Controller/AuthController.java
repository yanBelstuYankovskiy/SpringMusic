package com.valko.SpringMusic.Controller;

import com.valko.SpringMusic.Config.jwt.JwtProvider;
import com.valko.SpringMusic.DTO.AuthRequest;
import com.valko.SpringMusic.DTO.AuthResponse;
import com.valko.SpringMusic.DTO.RegistrationRequest;
import com.valko.SpringMusic.DTO.UserResponse;
import com.valko.SpringMusic.Entity.User;
import com.valko.SpringMusic.Service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
       User u = new User();
       u.setName(registrationRequest.getName());
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setAdmin(false);
        userService.save(u);
        return "OK";
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user != null ) {
            String token = jwtProvider.generateToken(user.getLogin());
            String role = user.isAdmin()?"ROLE_ADMIN":"ROLE_USER";
            AuthResponse response = new AuthResponse(token, role);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            throw new ControllerException("not such user");
//        }
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String role = userEntity.isAdmin()?"ROLE_ADMIN":"ROLE_USER";
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token,role);
    }

    @PostMapping("/authorized")
    public ResponseEntity<?> isAuthorized(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getUser")
    public UserResponse getUser(@RequestHeader(name = "Authorization") String jwt)  {
        String userName = jwtProvider.getLoginFromToken(jwt.substring(7));
        User user = userService.findByLogin(userName);
        String role = user.isAdmin()?"ROLE_ADMIN":"ROLE_USER";

        return new UserResponse(user.getId(), user.getLogin(), role);
    }
}
