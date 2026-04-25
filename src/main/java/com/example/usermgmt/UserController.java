package com.example.usermgmt.controller;
import com.example.usermgmt.dto.UserRequest;
import com.example.usermgmt.model.User;
import com.example.usermgmt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }
    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUsers(); }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserRequest request) { return userService.createUser(request); }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) { return userService.updateUser(id, request); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) { userService.deleteUser(id); }
}
