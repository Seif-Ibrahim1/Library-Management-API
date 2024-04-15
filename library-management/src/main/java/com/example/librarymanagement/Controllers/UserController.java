package com.example.librarymanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.Entities.AuthRequest;
import com.example.librarymanagement.Entities.UserInfo;
import com.example.librarymanagement.Services.JwtService;
import com.example.librarymanagement.Services.UserInfoService; 
  
@RestController
@RequestMapping("/auth") 
public class UserController { 

    @Autowired
    private UserInfoService service; 

    @Autowired
    private JwtService jwtService; 

    @Autowired
    private AuthenticationManager authenticationManager; 
  
    @PostMapping("/register") 
    public String addNewUser(@RequestBody UserInfo userInfo) { 
        return service.addUser(userInfo); 
    } 
  
    @PostMapping("/generateToken") 
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            return jwtService.generateToken(authRequest.getUsername()); 
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    } 
  
} 