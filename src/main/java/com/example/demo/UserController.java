package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return userService.mapToDto(savedUser);
    }

    @PostMapping("/{userId}/addresses")
    public AddressDto addAddress(@PathVariable Long userId, @RequestBody Address address) {
        Address savedAddress = userService.addAddresstoUser(userId, address);
        return userService.mapAddressToDto(savedAddress);
    }

    @GetMapping("/{userId}/addresses")
    public UserDto getUserAddresses(@PathVariable Long userId) {
        return userService.getUserWithAddresses(userId);
    }

}
