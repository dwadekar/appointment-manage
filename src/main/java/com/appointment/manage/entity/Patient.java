package com.appointment.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patient_Details")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Title is required")
    @Column
    private String title;

    @NotEmpty(message = "Patient Name is required")
    @Column(name = "patient_name")
    private String patientName;

    @NotEmpty(message = "Phone Number is required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Email is required")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    @Column(name = "email")
    private String email;
}
