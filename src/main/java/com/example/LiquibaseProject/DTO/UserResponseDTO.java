package com.example.LiquibaseProject.DTO;

import com.example.LiquibaseProject.Model.Company;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;

    private String name;

    private String address;

    private String phone_number;

}
