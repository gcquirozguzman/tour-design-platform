package com.tourdesign.platform.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    private String gender;

    private String nationality;

    private String email;

    private String phone;

    @Column(name = "numberOfChildren")
    private Integer numberOfChildren;

    private String occupation;

    @Column(name = "annualIncome")
    private Double annualIncome;

    private String languages;

    @Column(name = "registrationDate")
    private LocalDateTime registrationDate;

    private Boolean active;

}