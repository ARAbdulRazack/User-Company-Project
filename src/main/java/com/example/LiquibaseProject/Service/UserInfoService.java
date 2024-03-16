package com.example.LiquibaseProject.Service;

import com.example.LiquibaseProject.DTO.UserInfoRequestDTO;
import com.example.LiquibaseProject.DTO.UserInfoResponseDTO;
import com.example.LiquibaseProject.Mapper.UserInfoMapper;
import com.example.LiquibaseProject.Model.UserInfo;
import com.example.LiquibaseProject.Filter.UserInfoDetails;
import com.example.LiquibaseProject.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = userInfoRepository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String createUser(UserInfoRequestDTO userInfoRequestDTO) {
        UserInfo userInfo = userInfoMapper.mapToEntity(userInfoRequestDTO);
        userInfo.setPassword(encoder.encode(userInfoRequestDTO.getPassword()));
        userInfoRepository.save(userInfo);
        return "User Added Successfully";
    }

    public Optional<UserInfoResponseDTO> findByUsername(String username) {
        return userInfoRepository.findByName(username)
                .map(userInfoMapper::mapToDTO);
    }

    public UserInfoResponseDTO updateUser(String username, UserInfoRequestDTO updatedUserDTO) {
        Optional<UserInfo> existingUserOptional = userInfoRepository.findByName(username);

        if (existingUserOptional.isPresent()) {
            UserInfo existingUser = existingUserOptional.get();

            // Update fields based on non-null values in the DTO
            if (updatedUserDTO.getName() != null) {
                existingUser.setName(updatedUserDTO.getName());
            }
            if (updatedUserDTO.getPassword() != null) {
                existingUser.setPassword(encoder.encode(updatedUserDTO.getPassword()));
            }
            if (updatedUserDTO.getEmail() != null) {
                existingUser.setEmail(updatedUserDTO.getEmail());
            }
            if (updatedUserDTO.getPhone_number() != null) {
                existingUser.setPhone_number(updatedUserDTO.getPhone_number());
            }

            return userInfoMapper.mapToDTO(userInfoRepository.save(existingUser));
        } else {
            return null;
        }

    }

    public void deleteUser(String username) {
        userInfoRepository.deleteByName(username);
    }

}

