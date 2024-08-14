package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doctor.entity.DoctorConsultation;
import com.doctor.repo.DoctorConsultationRepo;

@Service
public class DoctorConsultationServiceImpl implements DoctorConsultationService {

	public final DoctorConsultationRepo repo;

	public DoctorConsultationServiceImpl(DoctorConsultationRepo repo) {
		super();
		this.repo = repo;
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

}
