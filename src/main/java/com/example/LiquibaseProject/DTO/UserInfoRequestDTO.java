package com.example.LiquibaseProject.DTO;

import lombok.Data;

@Data
public class UserInfoRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phone_number;

}
