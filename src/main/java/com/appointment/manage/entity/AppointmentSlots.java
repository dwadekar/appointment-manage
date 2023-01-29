package com.appointment.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointment_Slots")
public class AppointmentSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NotEmpty(message = "Start time is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @NotEmpty(message = "End time is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @NotNull(message = "Doctor is required")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
}
