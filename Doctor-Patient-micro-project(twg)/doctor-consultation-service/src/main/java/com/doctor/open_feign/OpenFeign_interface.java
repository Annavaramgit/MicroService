
package com.doctor.open_feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doctor.dto.Patient;

//@FeignClient("patient-registration-service")
@FeignClient("API-GATEWAY")
public interface OpenFeign_interface {
	
	@GetMapping("/patient//get-patient-by-id/{id}")
	Patient getPatientDetails(@PathVariable("id") long patientId);
		
	

}
