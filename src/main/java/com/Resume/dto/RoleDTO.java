package com.Resume.dto;

import com.Resume.model.Role;
import com.Resume.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {
    public Long id;
    public Status status;
    public String name;

    public static List<RoleDTO> fromToRoles (List<Role> roles) {
        return roles.stream().map(RoleDTO::roleDTO).collect(Collectors.toList());
    }
    public static   RoleDTO roleDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .status(role.getStatus())
                .name(role.getName())
                .build();
    }
}
