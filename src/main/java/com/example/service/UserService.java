package com.example.service;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> listAllUsers();

    List<UserDTO> listAllByRole(String role);

    UserDTO findByUserName(String userName);

    void save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void deleteByUserName(String userName);

    void delete(String username);
}
