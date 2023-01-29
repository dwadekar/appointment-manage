package com.appointment.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private AppointmentSlots appointmentSlots;
}
