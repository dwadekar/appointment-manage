package com.appointment.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Doctor_Details")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NotEmpty(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "First Name is required")
    @Size(min = 2, max = 100, message = "The length of first name must be between 2 and 100 characters.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    @Size(min = 2, max = 100, message = "The length of last name must be between 2 and 100 characters.")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Phone Number is required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Email is required")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    @Column(name = "email")
    private String email;
}
