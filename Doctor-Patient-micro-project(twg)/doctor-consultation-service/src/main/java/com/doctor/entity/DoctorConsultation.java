package com.doctor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorConsultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long doctorId;
	private String doctorName;
	private long patientId;
	private String observation;
	private String medication;
	
	
}
