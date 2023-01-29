package com.appointment.manage.controller;

import com.appointment.manage.entity.Appointments;
import com.appointment.manage.entity.Patient;
import com.appointment.manage.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody @Valid Patient patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return patientService.getPatientsDetails();
    }

    @GetMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getDoctorDetails(@PathVariable("id") long patientId) {
        return patientService.getPatient(patientId);
    }

    @PostMapping("/appointments/{appointment-id}/patients/{patient-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointments reserveAppointment(@PathVariable("appointment-id") long appointmentId,
                                           @PathVariable("patient-id") long patientId) {
        return patientService.reserveAppointment(appointmentId, patientId);
    }
}
