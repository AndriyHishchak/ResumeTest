package com.Resume.dto;

import com.Resume.model.Status;
import com.Resume.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private List<RoleDTO> roles;
    private Date update;
    private Date create;

    public User toAdmin() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setStatus(status);
        return user;
    }

    public static AdminUserDTO fromUser(User user) {
        List<RoleDTO> roleDTOS = RoleDTO.fromToRoles(user.getRoles());
       return AdminUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .roles(roleDTOS)
                .update(user.getUpdate())
                .create(user.getCreate())
                .build();
    }

}