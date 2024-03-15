package com.example.LiquibaseProject.Controller;

import com.example.LiquibaseProject.DTO.UserCompanyRequestDTO;
import com.example.LiquibaseProject.DTO.UserCompanyResponseDTO;
import com.example.LiquibaseProject.Model.UserCompany;
import com.example.LiquibaseProject.Service.UserCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-companies")
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    public UserCompanyController(UserCompanyService userCompanyService) {
        this.userCompanyService = userCompanyService;
    }

    @GetMapping  //http://localhost:8080/api/user-companies?userId=abc
    public List<UserCompanyResponseDTO> getUserCompanies(@RequestParam(required = false) Long userId,
                                                         @RequestParam(required = false) Long companyId,
                                                         @RequestParam(required = false) Long userCompanyId) {
        return userCompanyService.getUserCompanies(userId, companyId, userCompanyId);
    }

    @GetMapping("/filter")
    public List<UserCompany> getUserCompaniesByUserNameAndCompanyName(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String companyName) {
        return userCompanyService.getUserCompaniesByUserNameAndCompanyName(userName, companyName);
    }

    @GetMapping("/{field}")
    public List<UserCompanyResponseDTO> getUserCompaniesSorting(@PathVariable String field) {
        return userCompanyService.getProductWithSorting(field);
    }

    @GetMapping("/{offSet}/{pageSize}")
    public Page<UserCompanyResponseDTO> getUserCompaniesPagination(@PathVariable int offSet, @PathVariable int pageSize) {
        return userCompanyService.getProductWithPagination(offSet, pageSize);
    }

    @GetMapping("/{offSet}/{pageSize}/{field}")
    public Page<UserCompanyResponseDTO> getUserCompaniesPaginationAndSorting(@PathVariable int offSet, @PathVariable int pageSize, @PathVariable String field) {
        return userCompanyService.getProductWithPaginationAndSorting(offSet, pageSize, field);
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
