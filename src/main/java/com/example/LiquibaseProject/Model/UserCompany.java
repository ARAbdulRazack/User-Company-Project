package com.example.LiquibaseProject.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usercompany")
public class UserCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}