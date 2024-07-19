package com.kapasiya.springsecurity.controllers;


import com.kapasiya.springsecurity.entity.User;
import com.kapasiya.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@EnableWebSecurity
@EnableMethodSecurity
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> user = this.userService.getAllUser();

        if(user.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.ok(user);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<List<User>> addUser(@RequestBody User user)
    {
        List<User> users = this.userService.getAllUser();
        if(user.getUsername() ==  null || user.getUsername().isEmpty() || user.getPassword() ==  null || user.getPassword().isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            this.userService.addUser(user);
        }
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/getUser/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName)
    {
        if(userName.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        User user = this.userService.getUserByUserName(userName);

        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable String userName)
    {
        if(userName.trim().isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        this.userService.deleteUserByUserName(userName);
        List<User> users = this.userService.getAllUser();

        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateUser")
    public ResponseEntity<List<User>> updateUserData(@RequestBody User user)
    {
        if (user.getUsername()==null || user.getUsername().isEmpty() || user.getPassword() ==  null || user.getPassword().isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        this.userService.updateUser(user);
        List<User> users = this.userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
