package com.example.LiquibaseProject.Repository;

import com.example.LiquibaseProject.Model.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

    List<UserCompany> findByUserId(Long userId);

    List<UserCompany> findByCompanyId(Long companyId);
}