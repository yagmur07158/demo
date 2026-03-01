package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String city;
    private String street;
    private int buildingNo;
}
