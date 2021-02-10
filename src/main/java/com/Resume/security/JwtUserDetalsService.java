package com.Resume.security;

import com.Resume.model.User;
import com.Resume.security.Jwt.JwtUser;
import com.Resume.security.Jwt.JwtUserFactory;
import com.Resume.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetalsService implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public JwtUserDetalsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userService.findByUsername(username);

      if(user == null) {
          throw new UsernameNotFoundException("User with username "+ username +  "not found");
      }

      JwtUser jwtUser = JwtUserFactory.create(user);

      log.info("IN loadByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
