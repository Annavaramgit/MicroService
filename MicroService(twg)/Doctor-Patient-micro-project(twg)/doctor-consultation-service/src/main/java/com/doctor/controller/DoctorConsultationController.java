package com.doctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.DoctorConsultation;
import com.doctor.service.DoctorConsultationService;

@RestController
@RequestMapping("/doctor")
public class DoctorConsultationController {

	public final DoctorConsultationService doctorConsultationService;

	public DoctorConsultationController(DoctorConsultationService doctorConsultationService) {
		super();
		this.doctorConsultationService = doctorConsultationService;
	}

	/* save DoctorConsultation */
	@PostMapping("/save-DoctorConsultation")
	public ResponseEntity<DoctorConsultation> saveDoctorConsultation(
			@RequestBody DoctorConsultation DoctorConsultation) {

		DoctorConsultation DoctorConsultation1 = doctorConsultationService.saveConsultation(DoctorConsultation);

		if (DoctorConsultation1 != null) {

			return new ResponseEntity<>(DoctorConsultation1, HttpStatus.CREATED);
		} else {
		
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	/* get specific DoctorConsultation details */
	@GetMapping("/get-DoctorConsultation-by-id/{id}")
	public ResponseEntity<DoctorConsultation> getSpecific(@PathVariable long id) {

		DoctorConsultation DoctorConsultation = doctorConsultationService.findSpecific(id);

		if (DoctorConsultation != null) {

			return new ResponseEntity<>(DoctorConsultation, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	/* get all the DoctorConsultations list */
	@GetMapping("/get-all-DoctorConsultations")
	public ResponseEntity<Map<String, List<DoctorConsultation>>> getAll() {

		List<DoctorConsultation> listOfDoctorConsultations = doctorConsultationService.findAll();
		Map<String, List<DoctorConsultation>> map = new HashMap<>();

		if (!listOfDoctorConsultations.isEmpty()) {

			map.put("result", listOfDoctorConsultations);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("result", null);
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

}
