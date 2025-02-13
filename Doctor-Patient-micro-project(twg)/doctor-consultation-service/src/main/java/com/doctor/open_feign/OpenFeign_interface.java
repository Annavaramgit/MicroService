
package com.doctor.open_feign;

import java.util.concurrent.CompletableFuture;

import com.doctor.config.PatientServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.doctor.dto.Patient;


//@FeignClient(name = "patient-registration-service", url = "${patient-service.base-url}")
public interface OpenFeign_interface {

    @RequestMapping(method = RequestMethod.GET)
    Patient getPatientDetails(@RequestParam("id") Long id);

    @PostMapping
    Patient savePatient(@RequestBody Patient patient);

/*
    @RequestMapping(path="${patient-service.get-patient-url}/{id}",method=RequestMethod.GET)
    Patient getPatientDetails(@PathVariable("id") Long id);

    @PostMapping("${patient-service.save-patient-url}")
    Patient savePatient(@RequestBody Patient patient);

 */
/*
    @PostMapping("${patient-service.base-url}${patient-service.save-patient-url}")
    Patient savePatient(@RequestBody Patient patient);*/
    /*
    @GetMapping("${patient-service.base-url}${patient-service.get-patient-url}/{id}")
    Patient getPatientDetails(@PathVariable("id") long patientId);
    */

	/*
     //for asynchrnous pupose(feignclient calls asynchronous calls
	//@Async("asyncTaskExecutor")
	@GetMapping("/patient//get-patient-by-id/{id}")
	CompletableFuture<Patient> getPatientDetails(@PathVariable("id") long patientId);
    */


}
