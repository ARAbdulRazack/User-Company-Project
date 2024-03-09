package com.example.LiquibaseProject.Mapper;

import com.example.LiquibaseProject.DTO.UserRequestDTO;
import com.example.LiquibaseProject.Model.Company;
import com.example.LiquibaseProject.Model.User;
import com.example.LiquibaseProject.DTO.UserResponseDTO;
import com.example.LiquibaseProject.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private CompanyRepository companyRepository;

    public User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setAddress(userRequestDTO.getAddress());
        user.setPhone_number(userRequestDTO.getPhone_number());
        return user;
    }

    public UserResponseDTO toDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAddress(user.getAddress());
        dto.setPhone_number(user.getPhone_number());
        return dto;

    }

    public void updateEntityFromDTO(UserRequestDTO userRequestDTO, User existingUser) {
        existingUser.setName(userRequestDTO.getName());
        existingUser.setAddress(userRequestDTO.getAddress());
        existingUser.setPhone_number(userRequestDTO.getPhone_number());
    }
}
