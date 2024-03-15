package com.example.LiquibaseProject.DTO;

import lombok.Data;

@Data
public class UserInfoResponseDTO {
    private int id;
    private String name;
    private String email;
    private String phone_number;
}
