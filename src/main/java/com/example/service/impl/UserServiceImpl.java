package com.example.service.impl;

import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        return userRepository.findAllByRoleDescription(role).stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);
        return userMapper.convertToDTO(user);
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        // find current user
        User user = userRepository.findByUserName(userDTO.getUserName());
        // map updated user dto to entity
        User convertedUser = userMapper.convertToEntity(userDTO);
        // set id to converted entity
        convertedUser.setId(user.getId());
        // save updated use
        userRepository.save(convertedUser);

        return findByUserName(userDTO.getUserName());
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
