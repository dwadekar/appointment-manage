package com.appointment.manage.service;

import com.appointment.manage.entity.Appointments;
import com.appointment.manage.entity.Patient;
import com.appointment.manage.exception.RecordNotFoundException;
import com.appointment.manage.repository.AppointmentsRepository;
import com.appointment.manage.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;
    private AppointmentsRepository appointmentsRepository;
    private AppointmentSlotsService appointmentSlotsService;

    @Transactional
    public Patient createPatient(final Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional(readOnly = true)
    public List<Patient> getPatientsDetails() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Patient getPatient(Long patientId) {
        var patient = patientRepository.findById(patientId);
        return patient.orElseThrow(() -> new RecordNotFoundException(HttpStatus.NOT_FOUND.name(),
                String.format("Patient with ID %s not found", patientId)));
    }

    @Transactional
    public Appointments reserveAppointment(long appointmentId, long patientId) {
        var appointment = appointmentSlotsService.getAppointment(appointmentId);
        var patient = getPatient(patientId);
        var appointmentsReserved = Appointments.builder()
                .appointmentSlots(appointment)
                .patient(patient)
                .build();
        return appointmentsRepository.save(appointmentsReserved);

    }
}
