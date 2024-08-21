package com.doctor.service;

import java.util.List;

import com.doctor.dto.Patient;
import com.doctor.entity.DoctorConsultation;



public interface DoctorConsultationService {

	public DoctorConsultation saveConsultation(DoctorConsultation  consultation);
	public  DoctorConsultation modifyConsultation(DoctorConsultation  consulatation);
	public String deleteConsultation(long  id);
	public List<DoctorConsultation> findAll();
	public DoctorConsultation  findSpecific(long id);
	public List<DoctorConsultation> findByPatientId(long patientId);
	public Patient getPatient(long patientId);
}
