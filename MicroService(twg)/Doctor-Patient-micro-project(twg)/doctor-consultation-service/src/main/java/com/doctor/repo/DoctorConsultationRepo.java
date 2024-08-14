package com.doctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctor.entity.DoctorConsultation;

@Repository
public interface DoctorConsultationRepo extends JpaRepository<DoctorConsultation, Long> {
	
	List<DoctorConsultation> findByPatientId(long patientId);
}
