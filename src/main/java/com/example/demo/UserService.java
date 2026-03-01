package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
    public Address addAddresstoUser(Long userId, Address address) {
        Optional<User> users = userRepository.findById(userId);
        if (users.isPresent()) {
            address.setUser(users.get());
            users.get().getAddressesOfUser().add(address);
            return addressRepository.save(address);
        }
        throw new RuntimeException("User not found");

    }

    public User getAddressesByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Cacheable(value = "usersWithAddresses", key = "#userId")
    public UserDto getUserWithAddresses(Long userId) {
        System.out.println("DB’ye gidiliyor: userId = " + userId);
        User user = userRepository.findUserWithAddresses(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDto(user);
    }

    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAddresses(
                Optional.ofNullable(user.getAddressesOfUser())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(address -> {
                            AddressDto adto = new AddressDto();
                            adto.setId(address.getId());
                            adto.setCity(address.getCity());
                            adto.setStreet(address.getStreet());
                            adto.setBuildingNo(address.getBuilding_no());
                            return adto;
                        })
                        .collect(Collectors.toList())
        );
        return dto;
    }

    public AddressDto mapAddressToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setBuildingNo(address.getBuilding_no());
        return addressDto;
    }
}
