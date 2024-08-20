package com.patient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.entity.Patient;
import com.patient.service.PatientServiceInterface;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {

	public final PatientServiceInterface patientServiceInterface;

	public PatientController(PatientServiceInterface patientServiceInterface) {
		super();
		this.patientServiceInterface = patientServiceInterface;
	}

	/* get specific patient details */
	@GetMapping("/get-patient-by-id/{id}")
	public ResponseEntity<Patient> getSpecific(@PathVariable long id) {
		log.info("patient-reg-serv controller{}"+id);
		Patient patient = patientServiceInterface.findSpecific(id);
		
		if (patient != null) {
			
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	/* save patient */
	@PostMapping("/save-patient")
	public ResponseEntity<Patient>savePatient(@RequestBody Patient patient) {

		Patient patient1 = patientServiceInterface.savePatient(patient);
	

		if (patient1 != null) {
		
			return new ResponseEntity<Patient>(patient1, HttpStatus.CREATED);
		} else {
			patient1 = null;
			return new ResponseEntity<Patient>(patient1, HttpStatus.BAD_REQUEST);
			
		}
	}

	/* update purpose */
	@PutMapping("/modify-patient")
	public ResponseEntity<Map<String, Patient>> modify(@RequestBody Patient patient) {

		Patient patient1 = patientServiceInterface.modifyPatient(patient);
		Map<String, Patient> info = new HashMap<>();

		if (patient1 != null) {
			info.put("result", patient1);
			return new ResponseEntity<Map<String, Patient>>(info, HttpStatus.CREATED);
		} else {
			info.put("result", null);
			return new ResponseEntity<Map<String, Patient>>(info, HttpStatus.BAD_REQUEST);
		}
	}

	/* delete patient */
	@DeleteMapping("/delete-patient-by-id/{id}")
	public ResponseEntity<Map<String, String>> deletePatient(@PathVariable long id) {
		Map<String, String> del = new HashMap<>();
		del.put("Result", patientServiceInterface.deletePatient(id));
		return new ResponseEntity<Map<String, String>>(del, HttpStatus.OK);
	}

	/*get all the patients list */	
	@GetMapping("/get-all-patients")
	public ResponseEntity<Map<String,List<Patient>>> getAll(){
		List<Patient> listOfPatients=patientServiceInterface.findAll();
		Map<String, List<Patient>> map = new HashMap<>();
		if(!listOfPatients.isEmpty()) {
			map.put("result",listOfPatients);
			return new ResponseEntity<>(map,HttpStatus.OK);	
		}
		else {
			map.put("result",null);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);	
		}
		
	}
	
}
