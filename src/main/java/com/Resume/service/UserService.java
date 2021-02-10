package com.Resume.service;

import com.Resume.dto.UserDto;
import com.Resume.model.Role;
import com.Resume.model.User;

import java.util.List;

public interface UserService {
  User register(User user);

  List<UserDto> getAll();

  User findByUsername(String username);

  User findById (Long id);

  void delete (Long id);

  void deleteAll ();
  User addRoleAdmin(Long id);
  User update (Long id, User user);
  User updateMe (String username, User user);
}
