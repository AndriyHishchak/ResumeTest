package com.Resume.rest;

import com.Resume.dto.AdminUserDTO;
import com.Resume.dto.UserDto;
import com.Resume.model.Role;
import com.Resume.model.User;
import com.Resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {
    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username/{name}}")
    public ResponseEntity<AdminUserDTO> findUsername(@PathVariable("name")String name){
        User user = userService.findByUsername(name);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDTO result = AdminUserDTO.fromUser(user);
       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminUserDTO> getUserId(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDTO result = AdminUserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("showMe")
    public ResponseEntity<AdminUserDTO> showMe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User me = userService.findByUsername(name);
        AdminUserDTO result = AdminUserDTO.fromUser(me);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "Delete Succsefully id - " + id;
    }
    @DeleteMapping("delete/all")
    public String deleteAll() {
        userService.deleteAll();
        return "IN delete All Users";
    }

    @DeleteMapping("deleteMe")
    public String deleteMe(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User me = userService.findByUsername(name);
        userService.findById(me.getId());
        return "Delete Succsefully id - " + me.getId();
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<AdminUserDTO> patchBook(@PathVariable("id")Long id,
                                             @RequestBody User userPath) {
        User userUpdate = userService.update(id, userPath);

        AdminUserDTO result = AdminUserDTO.fromUser(userUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("{id}/addRole/admin/")
    public ResponseEntity<AdminUserDTO> addRole(@PathVariable("id")Long id) {
        User userUpdate = userService.addRoleAdmin(id);
        AdminUserDTO result = AdminUserDTO.fromUser(userUpdate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
