package com.Resume.rest;

import com.Resume.dto.AdminUserDTO;
import com.Resume.dto.UserDto;
import com.Resume.model.User;
import com.Resume.repository.UserRepository;
import com.Resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users/")
public class UserRestControllerV1 {
    private final UserService userService;


    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDto> findUsername(@PathVariable("username") String username){
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserId(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("showMe")
    public ResponseEntity<UserDto> showMe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User me = userService.findByUsername(name);
        UserDto result = UserDto.fromUser(me);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @DeleteMapping("deleteMe")
    public String deleteMe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User me = userService.findByUsername(name);
        userService.delete(me.getId());
        return "Delete Succsefully id - " + me.getId();
    }

    @PatchMapping("updateMe")
    public ResponseEntity<UserDto> patchBook(
                          @RequestBody User userPath) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User userUpdate = userService.updateMe(username, userPath);

        UserDto result = UserDto.fromUser(userUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
