package com.example.LiquibaseProject.Controller;

import com.example.LiquibaseProject.DTO.UserCompanyRequestDTO;
import com.example.LiquibaseProject.DTO.UserCompanyResponseDTO;
import com.example.LiquibaseProject.Service.UserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usercompanies")
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    @Autowired
    public UserCompanyController(UserCompanyService userCompanyService) {
        this.userCompanyService = userCompanyService;
    }

    @GetMapping
    public List<UserCompanyResponseDTO> getAllUserCompanies() {
        return userCompanyService.getAllUserCompanies();
    }

    @GetMapping("/{id}")
    public UserCompanyResponseDTO getUserCompanyById(@PathVariable Long id) {
        return userCompanyService.getUserCompanyById(id);
    }

    @GetMapping("/{userId}/users")
    public List<UserCompanyResponseDTO> getUserDetailsByUserId(@PathVariable Long userId) {
        return userCompanyService.getUserCompaniesByUserId(userId);
    }

    @GetMapping("/{companyId}/companies")
    public List<UserCompanyResponseDTO> getCompanyDetailsByCompanyId(@PathVariable Long companyId) {
        return userCompanyService.getUserCompaniesByCompanyId(companyId);
    }

    @PostMapping
    public UserCompanyResponseDTO createUserCompany(@RequestBody UserCompanyRequestDTO userCompanyRequestDTO) {
        return userCompanyService.createUserCompany(userCompanyRequestDTO);
    }

    @PutMapping("/{id}")
    public UserCompanyResponseDTO updateUserCompany(@PathVariable Long id, @RequestBody UserCompanyRequestDTO userCompanyRequestDTO) {
        return userCompanyService.updateUserCompany(id, userCompanyRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserCompany(@PathVariable Long id) {
        userCompanyService.deleteUserCompany(id);
    }
}
