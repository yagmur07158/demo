package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private List<AddressDto> addresses;
    private String email;
}
