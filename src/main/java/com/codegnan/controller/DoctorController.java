package com.codegnan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.Doctor;
import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidDoctorIDException;
import com.codegnan.service.DoctorService;
import com.codegnan.service.PatientService;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class DoctorController {
    private final DoctorService doctorService;
    private final PatientService patientService;

    public DoctorController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable int id) throws InvalidDoctorIDException {
        Doctor doctor = doctorService.findDoctorById(id);
        return ResponseEntity.ok(doctor); // Return 200 OK with the doctor entity
    }
    
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() throws InvalidDoctorIDException {
        List<Doctor> doctors = doctorService.findAllDoctors();
        return ResponseEntity.ok(doctors); // Return 200 OK with the list of doctors
    }
    
    @GetMapping("/{id}/patients") // Corrected the endpoint path to "/{id}/patients"
    public ResponseEntity<List<Patient>> getPatientByDoctor(@PathVariable int id) throws InvalidDoctorIDException {
        Doctor doctor = doctorService.findDoctorById(id);
        List<Patient> patients = patientService.findPatientByDoctor(doctor);
        return ResponseEntity.ok(patients); // Return 200 OK with the list of patients
    }
    
    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.saveDoctor(doctor); 
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor); // Return 201 Created with the saved doctor entity
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> editDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor) throws InvalidDoctorIDException {
        if (id != doctor.getId()) {
            throw new InvalidDoctorIDException("Doctor ID does not match");
        }
        Doctor editedDoctor = doctorService.editDoctor(doctor);
        return ResponseEntity.ok(editedDoctor); // Return 200 OK with the edited doctor entity
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") int id) throws InvalidDoctorIDException {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
    }
}
