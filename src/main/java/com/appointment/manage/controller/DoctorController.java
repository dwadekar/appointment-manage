package com.appointment.manage.controller;

import com.appointment.manage.entity.AppointmentSlots;
import com.appointment.manage.entity.Doctor;
import com.appointment.manage.service.AppointmentSlotsService;
import com.appointment.manage.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class DoctorController {
    private DoctorService doctorService;
    private AppointmentSlotsService appointmentSlotsService;

    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor createDoctor(@RequestBody @Valid Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/doctors")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getAllDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping("/doctors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getDoctorDetails(@PathVariable("id") long doctorId) {
        return doctorService.getDoctor(doctorId);
    }

    @PostMapping("/doctors/{id}/appointment-slots")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentSlots createDoctorAppointmentSlots(@RequestBody @Valid AppointmentSlots appointmentSlots,
                                                         @PathVariable("id") long doctorId) throws Exception {
        return appointmentSlotsService.createAppointmentSlots(appointmentSlots, doctorId);
    }

    @GetMapping("/doctors/{id}/appointment-slots")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentSlots> getAllAppointments(@PathVariable("id") long doctorId) throws Exception {
        return appointmentSlotsService.getAllAppointments(doctorId);
    }
}
