package com.geekbrains.controllers;

import com.geekbrains.controllers.dto.UserDto;
import com.geekbrains.controllers.dto.RoleDto;
import com.geekbrains.entities.User;
import com.geekbrains.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(value = "type", required = false) RoleDto type) {
        return userService.getAllUsersWithType(type);
    }

}
