package com.example.LiquibaseProject.DTO;

import lombok.Data;

@Data
public class UserCompanyRequestDTO {

    private UserResponseDTO userId;
    private CompanyResponseDTO companyId;

}
