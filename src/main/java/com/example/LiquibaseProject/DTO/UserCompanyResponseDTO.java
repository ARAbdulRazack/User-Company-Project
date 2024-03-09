package com.example.LiquibaseProject.DTO;

import lombok.Data;

@Data
public class UserCompanyResponseDTO {

    private Long id;
    private UserResponseDTO user;
    private CompanyResponseDTO company;

}
