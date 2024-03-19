package com.example.LiquibaseProject.DTO;

import lombok.Data;

@Data
public class UserCompanyRequestDTO {

    private UserResponseDTO user;
    private CompanyResponseDTO company;

    public UserResponseDTO getUserId() {
        return user;
    }

    public CompanyResponseDTO getCompanyId() {
        return company;
    }

}
