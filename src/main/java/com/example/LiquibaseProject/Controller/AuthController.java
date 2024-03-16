package com.example.LiquibaseProject.Controller;

import com.example.LiquibaseProject.DTO.AuthRequest;
import com.example.LiquibaseProject.DTO.UserInfoRequestDTO;
import com.example.LiquibaseProject.DTO.UserInfoResponseDTO;
import com.example.LiquibaseProject.Service.JwtService;
import com.example.LiquibaseProject.Service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/signIn")
    public String createser(@RequestBody UserInfoRequestDTO userInfoRequestDTO) {
        return service.createUser(userInfoRequestDTO);
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Optional<UserInfoResponseDTO> userProfile(Authentication authentication) {
        String name = authentication.getName();
        return service.findByUsername(name);
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public Optional<UserInfo> adminProfile(Authentication authentication) {
//        String name = authentication.getName();
//        return service.findByUsername(name);
//    }

    @PutMapping
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Optional<UserInfoResponseDTO> updateUser(Authentication authentication, @RequestBody UserInfoRequestDTO updatedUserDTO) {
        String name = authentication.getName();
        UserInfoResponseDTO updatedUser = service.updateUser(name, updatedUserDTO);
            return service.findByUsername(name);
    }

//    @PutMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public Optional<UserInfo> updateAdmin(Authentication authentication, @RequestBody UserInfo updatedUserInfo) {
//        String name = authentication.getName();
//        UserInfo updatedUser = service.updateUser(name, updatedUserInfo);
//        return service.findByUsername(name);
//    }

    @DeleteMapping
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Void deleteUser(Authentication authentication) {
        String name = authentication.getName();
        service.deleteUser(name);
        return null;
    }

//    @DeleteMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public Void deleteAdmin(Authentication authentication) {
//        String name = authentication.getName();
//        service.deleteUser(name);
//        return null;
//    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = jwtService.extractToken(request);
            jwtService.addToBlacklist(token);
            return ("Logout successful");
    }

}

