package com.kapasiya.springsecurity.controllers;


import com.kapasiya.springsecurity.entity.User;
import com.kapasiya.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController
{
    String str ="KapasiyaG1";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole({'USER','ADMIN'})")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<List<User>> addUser(@RequestBody User user)
    {
        this.userService.addUser(user);

        List<User> users = this.userService.getAllUser();
        if(users.isEmpty())
            ResponseEntity.noContent().build();

        return ResponseEntity.ok(users);

    }

    @PreAuthorize("hasRole({'USER','ADMIN'})")
    @GetMapping("/getUser/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName)
    {
        User user = this.userService.getUserByUserName(userName);

        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable String userName)
    {
        this.userService.deleteUserByUserName(userName);
        List<User> users = this.userService.getAllUser();

        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateUser")
    public ResponseEntity<List<User>> updateUserData(@RequestBody User user)
    {
        this.userService.updateUser(user);
        List<User> users = this.userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
