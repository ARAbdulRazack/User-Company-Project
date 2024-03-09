package com.example.LiquibaseProject.Service;

import com.example.LiquibaseProject.DTO.UserCompanyRequestDTO;
import com.example.LiquibaseProject.DTO.UserCompanyResponseDTO;
import com.example.LiquibaseProject.Mapper.UserCompanyMapper;
import com.example.LiquibaseProject.Model.UserCompany;
import com.example.LiquibaseProject.Repository.UserCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCompanyService {

    private final UserCompanyRepository userCompanyRepository;
    private final UserCompanyMapper userCompanyMapper;

    @Autowired
    public UserCompanyService(UserCompanyRepository userCompanyRepository, UserCompanyMapper userCompanyMapper) {
        this.userCompanyRepository = userCompanyRepository;
        this.userCompanyMapper = userCompanyMapper;
    }

    public List<UserCompanyResponseDTO> getAllUserCompanies() {
        List<UserCompany> userCompanies = userCompanyRepository.findAll();
        return userCompanies.stream()
                .map(userCompanyMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserCompanyResponseDTO getUserCompanyById(Long id) {
        UserCompany userCompany = userCompanyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserCompany not found with id: " + id));
        return userCompanyMapper.toDto(userCompany);
    }

    public List<UserCompanyResponseDTO> getUserCompaniesByUserId(Long userId) {
        List<UserCompany> userCompanies = userCompanyRepository.findByUserId(userId);
        return userCompanyMapper.toDtoList(userCompanies);
    }

    public List<UserCompanyResponseDTO> getUserCompaniesByCompanyId(Long companyId) {
        List<UserCompany> userCompanies = userCompanyRepository.findByCompanyId(companyId);
        return userCompanyMapper.toDtoList(userCompanies);
    }

    public UserCompanyResponseDTO createUserCompany(UserCompanyRequestDTO userCompanyRequestDTO) {
        UserCompany userCompany = userCompanyMapper.toEntity(userCompanyRequestDTO);
        userCompany = userCompanyRepository.save(userCompany);
        return userCompanyMapper.toDto(userCompany);
    }

    public UserCompanyResponseDTO updateUserCompany(Long id, UserCompanyRequestDTO userCompanyRequestDTO) {
        UserCompany existingUserCompany = userCompanyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserCompany not found with id: " + id));

        userCompanyMapper.updateEntityFromDTO(userCompanyRequestDTO, existingUserCompany);
        existingUserCompany = userCompanyRepository.save(existingUserCompany);

        return userCompanyMapper.toDto(existingUserCompany);
    }

    public void deleteUserCompany(Long id) {
        userCompanyRepository.deleteById(id);
    }
}
