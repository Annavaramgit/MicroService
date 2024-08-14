package com.patient.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.patient.entity.Patient;
import com.patient.repo.PatientRepo;

@Service
public class PatientServiceImpl implements PatientServiceInterface {

	public final PatientRepo patientRepo;
	
	public PatientServiceImpl(PatientRepo patientRepo) {
		
		this.patientRepo = patientRepo;
	}

	@Override
	public Patient savePatient(Patient patient) {
		
		return patientRepo.save(patient);
	}

	@Override
	public Patient modifyPatient(Patient patient) {
		
		return patientRepo.save(patient);
	}

	@Override
	public String deletePatient(long id) {
		
		Patient patient=patientRepo.findById(id).get();
		if(!patient.equals(null)) {
			patientRepo.delete(patient);
			return " deleted...";
		}
		return "not found/not deleted this id";
	}

	@Override
	public List<Patient> findAll() {
	
		return patientRepo.findAll();
	}

	@Override
	public Patient findSpecific(long id) {
		
		return patientRepo.findById(id).orElse(null);
	}

}
