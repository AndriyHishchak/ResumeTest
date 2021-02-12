package com.Resume.controller;

import com.Resume.dto.AuthenticationRequestDTO;
import com.Resume.model.User;
import com.Resume.rest.AuthenticationRestControllerV1;
import com.Resume.rest.RegistrationRestControllerV1;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {

   private AuthenticationRestControllerV1 auth;
   private RegistrationRestControllerV1 reg;

    public AuthenticationController(AuthenticationRestControllerV1 auth, RegistrationRestControllerV1 reg) {
        this.auth = auth;
        this.reg = reg;
    }
    
    @GetMapping("/infoPage")
    public String infoSave () {
        return "infoPage";
    }

    @GetMapping("/sign-in")
    public String getSingIn (){
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String getInfoPage (AuthenticationRequestDTO requestDto){

        auth.login(requestDto);

        return "redirect:/infoPage";
    }

    @GetMapping("/sign-up")
    public String getSingUp () {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registration(User user) {
        reg.registration(user);
        return "redirect:/sign-in";
    }


}
