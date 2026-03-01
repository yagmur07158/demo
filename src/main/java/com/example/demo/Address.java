package com.example.demo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name ="addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private int building_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="User_id")
    private User user;
}
