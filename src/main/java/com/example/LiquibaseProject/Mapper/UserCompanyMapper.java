package com.example.LiquibaseProject.Mapper;

import com.example.LiquibaseProject.DTO.CompanyResponseDTO;
import com.example.LiquibaseProject.DTO.UserCompanyRequestDTO;
import com.example.LiquibaseProject.DTO.UserResponseDTO;
import com.example.LiquibaseProject.Model.User;
import com.example.LiquibaseProject.Model.Company;
import com.example.LiquibaseProject.Model.UserCompany;
import com.example.LiquibaseProject.DTO.UserCompanyResponseDTO;
import com.example.LiquibaseProject.Repository.UserRepository;
import com.example.LiquibaseProject.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCompanyMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public UserCompany toEntity(UserCompanyRequestDTO userCompanyRequestDTO) {
        UserCompany userCompany = new UserCompany();

        if (userCompanyRequestDTO.getUserId() != null) {
            User user = userRepository.findById(userCompanyRequestDTO.getUserId().getId())
                    .orElseThrow();
            userCompany.setUser(user);
        }

        if (userCompanyRequestDTO.getCompanyId() != null) {
            Company company = companyRepository.findById(userCompanyRequestDTO.getCompanyId().getId())
                    .orElseThrow();
            userCompany.setCompany(company);
        }

        return userCompany;
    }

    public UserCompanyResponseDTO toDto(UserCompany userCompany) {
        UserCompanyResponseDTO dto = new UserCompanyResponseDTO();
        dto.setId(userCompany.getId());

        if (userCompany.getUser() != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(userCompany.getUser().getId());
            userResponseDTO.setName(userCompany.getUser().getName());
            userResponseDTO.setAddress(userCompany.getUser().getAddress());
            userResponseDTO.setPhone_number(userCompany.getUser().getPhoneNumber());
            dto.setUser(userResponseDTO);
        }

        if (userCompany.getCompany() != null) {
            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
            companyResponseDTO.setId(userCompany.getCompany().getId());
            companyResponseDTO.setName(userCompany.getCompany().getName());
            companyResponseDTO.setAddress(userCompany.getCompany().getAddress());
            companyResponseDTO.setPhone_number(userCompany.getCompany().getPhone_number());
            dto.setCompany(companyResponseDTO);
        }

        return dto;
    }

    public void updateEntityFromDTO(UserCompanyRequestDTO userCompanyRequestDTO, UserCompany existingUserCompany) {
        if (userCompanyRequestDTO.getUserId() != null) {
            User user = userRepository.findById(userCompanyRequestDTO.getUserId().getId())
                    .orElseThrow();
            existingUserCompany.setUser(user);
        }

        if (userCompanyRequestDTO.getCompanyId() != null) {
            Company company = companyRepository.findById(userCompanyRequestDTO.getCompanyId().getId())
                    .orElseThrow();
            existingUserCompany.setCompany(company);
        }
    }

    public List<UserCompanyResponseDTO> toDtoList(List<UserCompany> userCompanies) {
        return userCompanies.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
