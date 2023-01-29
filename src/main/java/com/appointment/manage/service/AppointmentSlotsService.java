package com.appointment.manage.service;

import com.appointment.manage.entity.AppointmentSlots;
import com.appointment.manage.exception.RecordNotFoundException;
import com.appointment.manage.repository.AppointmentSlotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentSlotsService {
    private AppointmentSlotsRepository appointmentSlotsRepository;
    private DoctorService doctorService;

    @Transactional
    public AppointmentSlots createAppointmentSlots(final AppointmentSlots appointmentSlots, long doctorId) throws Exception {
        appointmentSlots.setDoctor(doctorService.getDoctor(doctorId));
        return appointmentSlotsRepository.save(appointmentSlots);
    }

    @Transactional(readOnly = true)
    public List<AppointmentSlots> getAllAppointments(long doctorId) throws Exception {
        var doctor = doctorService.getDoctor(doctorId);
        return appointmentSlotsRepository.findByDoctor(doctor);
    }

    @Transactional(readOnly = true)
    public AppointmentSlots getAppointment(long appointmentId) {
        var appointment = appointmentSlotsRepository.findById(appointmentId);
        return appointment.orElseThrow(()-> new RecordNotFoundException(HttpStatus.NOT_FOUND.name(),
                String.format("Appointment with ID %s not found",appointmentId)));
    }
}
