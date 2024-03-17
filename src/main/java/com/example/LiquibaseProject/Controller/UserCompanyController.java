package com.example.LiquibaseProject.Controller;

import com.example.LiquibaseProject.DTO.UserCompanyRequestDTO;
import com.example.LiquibaseProject.DTO.UserCompanyResponseDTO;
import com.example.LiquibaseProject.Service.UserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-companies")
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    @Autowired
    public UserCompanyController(UserCompanyService userCompanyService) {
        this.userCompanyService = userCompanyService;
    }

    @GetMapping  //http://localhost:8080/api/user-companies?userId=abc onlu firat value will be taken
    public List<UserCompanyResponseDTO> getUserCompanies(@RequestParam(required = false) Long userId,
                                                         @RequestParam(required = false) Long companyId,
                                                         @RequestParam(required = false) Long userCompanyId) {
        return userCompanyService.getUserCompanies(userId, companyId, userCompanyId);
    }

    @GetMapping("/filter") //http://localhost:8080/api/user-companies/filter?userName=Abdul Razack&companyName=MDKaif&page=0&size=10
    public Page<UserCompanyResponseDTO> getUserCompaniesByUserNameAndCompanyName(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String companyName,
            Pageable pageable) {
        return userCompanyService.getUserCompaniesByUserNameAndCompanyName(userName, companyName, pageable);
    }

    @GetMapping("/sorting/{field}")
    public List<UserCompanyResponseDTO> getUserCompaniesSorting(@PathVariable String field) {
        return userCompanyService.getProductWithSorting(field);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}")
    public Page<UserCompanyResponseDTO> getUserCompaniesPagination(@PathVariable int offSet, @PathVariable int pageSize) {
        return userCompanyService.getProductWithPagination(offSet, pageSize);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}/{field}")
    public Page<UserCompanyResponseDTO> getUserCompaniesPaginationAndSorting(@PathVariable int offSet, @PathVariable int pageSize, @PathVariable String field) {
        return userCompanyService.getProductWithPaginationAndSorting(offSet, pageSize, field);
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
