package com.Resume.rest;

import com.Resume.dto.UserDto;
import com.Resume.model.User;
import com.Resume.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class RegistrationRestControllerV1 {

    private final UserService userService;


    public RegistrationRestControllerV1(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("registration")
    public ResponseEntity<UserDto> registration(@RequestBody User user){

        User userSign = userService.register(user);

        UserDto result = UserDto.fromUser(userSign);
        return new ResponseEntity<>(result, HttpStatus.OK);


    }
}
