package com.Resume.service.impl;

import com.Resume.dto.UserDto;
import com.Resume.model.Role;
import com.Resume.model.Status;
import com.Resume.model.User;
import com.Resume.repository.RoleRepository;
import com.Resume.repository.UserRepository;
import com.Resume.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<UserDto> getAll() {

        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.forEach(user -> userDtos.add(UserDto.fromUser(user)));

        log.info("IN getAll - {} users found", userDtos.size());
        return new ArrayList<>(userDtos);

    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} find by username: {}",user,username);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            log.warn("IN findByID - no user found by id: {}",id);
            return null;
        }
        log.info("IN findByID - user: {} find by id: {}",user,id);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id : {} ",id);
    }
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        log.info("IN delete All Users");
    }

    @Override
    public User addRoleAdmin(Long id) {
        User user = userRepository.findById(id).get();
        Role roleUser = roleRepository.findByName("ROLE_ADMIN");
        List<Role> userRoles = new ArrayList<>(user.getRoles());
        userRoles.add(roleUser);
        user.setRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(Long id,User userPath) {

        User userRefresh = userRepository.findById(id).get();
//        if(userPath.getUsername() !=null){
//            userRefresh.setUsername(userPath.getUsername());
//        }
        if(userPath.getFirstName().equals(userPath.getFirstName())) {
            userRefresh.setFirstName(userPath.getFirstName());
        }
        if(userPath.getLastName().equals(userPath.getLastName())) {
            userRefresh.setLastName(userPath.getLastName());
        }
        if(userPath.getEmail().equals(userPath.getEmail())) {
            userRefresh.setEmail(userPath.getEmail());
        }
        userRefresh.setUpdate(new Date());

        return userRepository.save(userRefresh);
    }



    @Override
    public User updateMe(String username, User userPath) {
        User userRefresh = userRepository.findByUsername(username);
//        if(userPath.getUsername() !=null){
//            userRefresh.setUsername(userPath.getUsername());
//        }
        if(userPath.getFirstName().equals(userPath.getFirstName())) {
            userRefresh.setFirstName(userPath.getFirstName());
        }
        if(userPath.getLastName().equals(userPath.getLastName())) {
            userRefresh.setLastName(userPath.getLastName());
        }
        if(userPath.getEmail().equals(userPath.getEmail())) {
            userRefresh.setEmail(userPath.getEmail());
        }
        userRefresh.setUpdate(new Date());

        return userRepository.save(userRefresh);
    }



}
