package com.example.LiquibaseProject.Service;

import com.example.LiquibaseProject.DTO.UserCompanyRequestDTO;
import com.example.LiquibaseProject.DTO.UserCompanyResponseDTO;
import com.example.LiquibaseProject.DTO.UserRequestDTO;
import com.example.LiquibaseProject.DTO.UserResponseDTO;
import com.example.LiquibaseProject.Mapper.UserMapper;
import com.example.LiquibaseProject.Model.User;
import com.example.LiquibaseProject.Model.UserCompany;
import com.example.LiquibaseProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserResponseDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserCompany not found with id: " + id));
        return userMapper.toDto(user);
    }

    public UserResponseDTO saveUser(UserRequestDTO requestDTO) {
        User user = userMapper.toEntity(requestDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserCompany not found with id: " + id));

            userMapper.updateEntityFromDTO(updatedUser, existingUser);
        existingUser = userRepository.save(existingUser);

        return userMapper.toDto(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
