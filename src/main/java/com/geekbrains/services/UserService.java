package com.geekbrains.services;

import com.geekbrains.controllers.dto.UserDto;
import com.geekbrains.controllers.dto.RoleDto;
import com.geekbrains.entities.Role;
import com.geekbrains.entities.User;
import com.geekbrains.exceptions.ManagerIsEarlierThanNeedException;
import com.geekbrains.exceptions.UnknownUserTypeException;
import com.geekbrains.exceptions.UserNotFoundException;
import com.geekbrains.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User saveUser(UserDto userDto) {
        if (userDto.getRoleDto().equals(RoleDto.MANAGER)) {
            saveManager(userDto);
        } else if (userDto.getRoleDto().equals(RoleDto.CUSTOMER)) {
            saveTypicallyUser(userDto);
        }

        throw new UnknownUserTypeException();
    }

    private User saveTypicallyUser(UserDto userDto) {
        User user = createUserFromDto(userDto);

        Role role = roleService.getByName("ROLE_USER");
        user.setRoles(List.of(role));

        return userRepository.save(user);
    }

    private User saveManager(UserDto userDto) {
        if (userDto.getAge() > 18) {
            User user = createUserFromDto(userDto);

            Role role = roleService.getByName("ROLE_MANAGER");
            user.setRoles(List.of(role));

            return userRepository.save(user);
        }

        throw new ManagerIsEarlierThanNeedException("Пользователь младше восемнадцати лет");
    }

    private User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAge(userDto.getAge());

        return user;
    }

    public List<User> getAllUsersWithType(RoleDto roleDto) {
        Role role;

        if (roleDto == RoleDto.CUSTOMER) {
            role = roleService.getByName("ROLE_CUSTOMER");
            return userRepository.findAllByRoles(role);
        } else if (roleDto == RoleDto.MANAGER) {
            role = roleService.getByName("ROLE_MANAGER");
            return userRepository.findAllByRoles(role);
        }

        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("Пользователь с идентификатором %s не найден", id)));
    }
}
