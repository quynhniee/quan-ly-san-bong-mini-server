package com.example.sanbongminiserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String taxCode;

    @NotEmpty
    @NotBlank
    @NotNull
    private String phoneNumber;

    private String address;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    private String note;


    @Column(name="deleted", columnDefinition="BOOLEAN DEFAULT false")
    private Boolean deleted = false;
}
