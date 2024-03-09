package com.example.LiquibaseProject.Service;

import com.example.LiquibaseProject.DTO.CompanyRequestDTO;
import com.example.LiquibaseProject.DTO.CompanyResponseDTO;
import com.example.LiquibaseProject.DTO.UserRequestDTO;
import com.example.LiquibaseProject.DTO.UserResponseDTO;
import com.example.LiquibaseProject.Mapper.CompanyMapper;
import com.example.LiquibaseProject.Mapper.UserMapper;
import com.example.LiquibaseProject.Model.Company;
import com.example.LiquibaseProject.Model.User;
import com.example.LiquibaseProject.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper userMapper;

    public List<CompanyResponseDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserCompany not found with id: " + id));
        return companyMapper.toDto(company);
    }

    public CompanyResponseDTO saveCompany(CompanyRequestDTO requestDTO) {
        Company company = companyMapper.toEntity(requestDTO);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    public CompanyResponseDTO updateCompany(Long id, CompanyRequestDTO updatedCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserCompany not found with id: " + id));

        companyMapper.updateEntityFromDTO(updatedCompany, existingCompany);
        existingCompany = companyRepository.save(existingCompany);

        return companyMapper.toDto(existingCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}

