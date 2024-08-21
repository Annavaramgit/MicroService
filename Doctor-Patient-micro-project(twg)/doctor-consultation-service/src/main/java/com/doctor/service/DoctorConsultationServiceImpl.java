package com.doctor.service;

import java.util.List;
import java.util.Optional;

import com.doctor.dto.Patient;
import com.doctor.open_feign.OpenFeign_interface;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.doctor.entity.DoctorConsultation;
import com.doctor.repo.DoctorConsultationRepo;

@Service
@Slf4j
public class DoctorConsultationServiceImpl implements DoctorConsultationService {


    public final DoctorConsultationRepo repo;
    public final OpenFeign_interface openFeign_interface;

    public DoctorConsultationServiceImpl(DoctorConsultationRepo repo, OpenFeign_interface openFeign_interface) {
        super();
        this.repo = repo;
        this.openFeign_interface = openFeign_interface;
        ;
    }

    @Override
    public DoctorConsultation saveConsultation(DoctorConsultation consultation) {

        return repo.save(consultation);
    }

    @Override
    public DoctorConsultation modifyConsultation(DoctorConsultation consulatation) {

        return repo.save(consulatation);
    }

    @Override
    public String deleteConsultation(long id) {
        Optional<DoctorConsultation> consultation = repo.findById(id);

        if (consultation.isPresent()) {
            try {
                repo.delete(consultation.get());
                return "Doctor consultation deleted successfully.";
            } catch (Exception e) {
                return "An error occurred while deleting the consultation.";
            }
        } else {
            return "Doctor consultation with the provided ID not found.";
        }
    }


    @Override
    public List<DoctorConsultation> findAll() {

        return repo.findAll();
    }

    @Override
    public DoctorConsultation findSpecific(long id) {

        return repo.findById(id).orElse(null);
    }

    @Override
    public List<DoctorConsultation> findByPatientId(long patientId) {

        return repo.findByPatientId(patientId);
    }

    @Override
    @CircuitBreaker(name = "doctor-service", fallbackMethod = "circuitBreakerHandler")
    public Patient getPatient(long patientId) {

        Patient patientDetails = openFeign_interface.getPatientDetails(patientId);
        log.info("This is doctor-service service getpateint" + patientDetails);
        return patientDetails;
    }

    public Patient circuitBreakerHandler(long patientId, Throwable e) {
        log.info("This circuitBreakerHandler");

        Patient patient = new Patient();
        patient.setId(0);
        patient.setName(null);
        patient.setContact(null);
        patient.setDetails(null);
        return patient;

    }

}
