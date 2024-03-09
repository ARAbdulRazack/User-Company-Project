package com.example.LiquibaseProject.Controller;

import com.example.LiquibaseProject.DTO.CompanyRequestDTO;
import com.example.LiquibaseProject.DTO.CompanyResponseDTO;
import com.example.LiquibaseProject.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponseDTO getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public CompanyResponseDTO saveCompany(@RequestBody CompanyRequestDTO companyRequestDTO) {
        return companyService.saveCompany(companyRequestDTO);
    }

    @PutMapping("/{id}")
    public CompanyResponseDTO updateCompany(@PathVariable Long id, @RequestBody CompanyRequestDTO companyRequestDTO) {
        return companyService.updateCompany(id, companyRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}