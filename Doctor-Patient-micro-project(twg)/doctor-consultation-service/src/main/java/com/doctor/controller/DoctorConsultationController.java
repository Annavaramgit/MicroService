package com.doctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.doctor.dto.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.DoctorConsultation;
import com.doctor.open_feign.OpenFeign_interface;
import com.doctor.service.DoctorConsultationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorConsultationController {

    public final DoctorConsultationService doctorConsultationService;
    public final OpenFeign_interface openFeign_interface;

    public DoctorConsultationController(DoctorConsultationService doctorConsultationService,
                                        OpenFeign_interface openFeign_interface) {
        super();
        this.doctorConsultationService = doctorConsultationService;
        this.openFeign_interface = openFeign_interface;
    }

    /* save DoctorConsultation*/
    @PostMapping("/save-DoctorConsultation")
    public ResponseEntity<DoctorConsultation> saveDoctorConsultation(

            @RequestBody DoctorConsultation doctorConsultation) {
        log.info("dc-con-serv con started");
        Patient patientDetails = openFeign_interface.getPatientDetails(doctorConsultation.getPatientId());
        System.out.println("-------------------------");
        log.info("dc-con-serv con get the patient{}" + patientDetails.toString());
        System.out.println(patientDetails);

        DoctorConsultation DoctorConsultation1 =
                doctorConsultationService.saveConsultation(doctorConsultation);

        if (DoctorConsultation1 != null) {

            return new ResponseEntity<>(DoctorConsultation1, HttpStatus.CREATED);
        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    /*for asynchronous fienclient http calls*/
    /*
    @PostMapping("/save-DoctorConsultation")
    public ResponseEntity<DoctorConsultation> saveDoctorConsultation(
            @RequestBody DoctorConsultation doctorConsultation) {
        log.info("dc-con-serv con started");

        // Initiate asynchronous call
        CompletableFuture<Patient> patientFuture = openFeign_interface.getPatientDetails(doctorConsultation.getPatientId());
        log.info("patient details"+patientFuture);
        System.out.println("dc-con-serv con received patient details: " + patientFuture);
        // Process the result asynchronously
        patientFuture.thenAccept(patient -> {
            log.info("dc-con-serv con get the patient: {}", patient);
            System.out.println("dc-con-serv con received patient details: " + patient);

            // Perform any additional logic with the patient details
        }).exceptionally(ex -> {
            log.error("Error fetching patient details", ex);
            System.out.println("dc-con-serv con received patient details: " + ex);

            return null;
        });

        DoctorConsultation savedConsultation = doctorConsultationService.saveConsultation(doctorConsultation);

        if (savedConsultation != null) {
            return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
   */

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
