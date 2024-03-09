package com.example.LiquibaseProject.Repository;

import com.example.LiquibaseProject.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
