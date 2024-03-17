package com.example.LiquibaseProject.Repository;

import com.example.LiquibaseProject.Model.UserCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

    List<UserCompany> findByUserId(Long userId);

    List<UserCompany> findByCompanyId(Long companyId);


    List<UserCompany> findByUserName(String userName);

    List<UserCompany> findByCompanyName(String companyName);

    List<UserCompany> findByUserNameAndCompanyName(String userName, String companyName);

    Page<UserCompany> findAll(Specification<UserCompany> spec, Pageable pageable);
}