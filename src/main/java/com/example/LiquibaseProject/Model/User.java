package com.example.LiquibaseProject.Model;

import jakarta.persistence.*;
import lombok.Data;

import javax.net.ssl.SSLSession;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone_number;

}
