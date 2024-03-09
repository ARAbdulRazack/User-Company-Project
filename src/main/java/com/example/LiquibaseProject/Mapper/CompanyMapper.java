package com.example.LiquibaseProject.Mapper;


import com.example.LiquibaseProject.DTO.CompanyRequestDTO;
import com.example.LiquibaseProject.DTO.CompanyResponseDTO;
import com.example.LiquibaseProject.Model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.getName());
        company.setAddress(companyRequestDTO.getAddress());
        company.setPhone_number(companyRequestDTO.getPhone_number());

        return company;
    }

    public CompanyResponseDTO toDto(Company company) {
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setAddress(company.getAddress());
        dto.setPhone_number(company.getPhone_number());

        return dto;
    }

    public void updateEntityFromDTO(CompanyRequestDTO companyRequestDTO, Company existingCompany) {
        existingCompany.setName(companyRequestDTO.getName());
        existingCompany.setAddress(companyRequestDTO.getAddress());
        existingCompany.setPhone_number(companyRequestDTO.getPhone_number());

    }
}