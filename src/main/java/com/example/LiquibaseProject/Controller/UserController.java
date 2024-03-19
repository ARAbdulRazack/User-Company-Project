package com.example.LiquibaseProject.Controller;

import com.example.LiquibaseProject.DTO.AuthRequestDTO;
import com.example.LiquibaseProject.DTO.UserRequestDTO;
import com.example.LiquibaseProject.DTO.UserResponseDTO;
import com.example.LiquibaseProject.JWT.JwtTokenUtil;
import com.example.LiquibaseProject.Mapper.UserMapper;
import com.example.LiquibaseProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenProvider;

    @GetMapping
    public List<UserResponseDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signin")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);

    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(id, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO authenticationRequest) {
        return userService.login(authenticationRequest.getName(), authenticationRequest.getPassword());
    }
}
