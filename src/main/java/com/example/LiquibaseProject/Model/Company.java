package com.example.LiquibaseProject.Model;

import jakarta.persistence.*;
import lombok.Data;

//import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone_number;

}
