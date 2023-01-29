package com.appointment.manage.service;

import com.appointment.manage.entity.Doctor;
import com.appointment.manage.exception.RecordNotFoundException;
import com.appointment.manage.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private DoctorRepository doctorRepository;

    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        var doctorResponse = doctorRepository.save(doctor);
        return doctorResponse;
    }

    @Transactional(readOnly = true)
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Doctor getDoctor(long doctorId) {
        var doctor = doctorRepository.findById(doctorId);
        return doctor.orElseThrow(()-> new RecordNotFoundException(HttpStatus.NOT_FOUND.name(),
                String.format("Doctor with ID %s not found", doctorId)));
    }
}
