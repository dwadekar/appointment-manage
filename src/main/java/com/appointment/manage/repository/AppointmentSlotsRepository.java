package com.appointment.manage.repository;

import com.appointment.manage.entity.AppointmentSlots;
import com.appointment.manage.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlots, Long> {
    List<AppointmentSlots> findByDoctor(Doctor doctor);
}
