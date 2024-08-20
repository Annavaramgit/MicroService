
package com.doctor.open_feign;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doctor.dto.Patient;

//@FeignClient("patient-registration-service") //this if api-gateway not there then directly give server micro-name
@FeignClient("API-GATEWAY")    //here api-gateway take responsibilty whcih micro need to call based on req url
public interface OpenFeign_interface {

    @GetMapping("/patient//get-patient-by-id/{id}")
    Patient getPatientDetails(@PathVariable("id") long patientId);



	/*
     //for asynchrnous pupose(feignclient calls asynchronous calls
	//@Async("asyncTaskExecutor")
	@GetMapping("/patient//get-patient-by-id/{id}")
	CompletableFuture<Patient> getPatientDetails(@PathVariable("id") long patientId);
    */


}
