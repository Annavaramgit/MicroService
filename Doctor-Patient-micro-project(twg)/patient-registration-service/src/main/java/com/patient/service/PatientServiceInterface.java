package com.patient.service;

import java.util.List;

import com.patient.entity.Patient;

public interface PatientServiceInterface {
	
	public Patient savePatient(Patient  patient);
	public  Patient modifyPatient(Patient  patient);
	public String deletePatient(long  id);
	public List<Patient> findAll();
	public Patient  findSpecific(long id);
	

}
