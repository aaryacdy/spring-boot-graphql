package com.arya.graphql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_admin")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;

    @Email
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;
}
