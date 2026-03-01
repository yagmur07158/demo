package com.example.demo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Entity
@Table (name ="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User{
    @Id
    @GeneratedValue (strategy = GenerationType. IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany (mappedBy = "user", cascade=CascadeType.ALL, fetch= FetchType.LAZY)
    //@JsonManagedReference
    List<Address> addressesOfUser; }