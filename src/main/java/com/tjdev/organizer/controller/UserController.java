package com.tjdev.organizer.controller;

import com.tjdev.organizer.security.model.LoginReq;
import com.tjdev.organizer.model.MyUser;
import com.tjdev.organizer.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/new")
    public String createUser(@RequestBody LoginReq loginReq){

        MyUser user = new MyUser(loginReq.username(), passwordEncoder.encode(loginReq.password()));

        userRepository.save(user);
        return "saved user to DB";
    }
}
