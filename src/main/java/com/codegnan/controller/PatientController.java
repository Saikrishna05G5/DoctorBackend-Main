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

import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidPatientIdException;
import com.codegnan.service.PatientService;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins ="*") // Allow requests from all origins
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatient(@PathVariable("id") int id) throws InvalidPatientIdException {
        Patient patient = patientService.findPatientById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(patient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatients() {
        List<Patient> patients = patientService.findAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> editPatient(@PathVariable("id") int id, @RequestBody Patient patient) throws InvalidPatientIdException {
        if (id != patient.getId()) {
            throw new InvalidPatientIdException("id " + id + " does not match patient.id " + patient.getId());
        }
        Patient editedPatient = patientService.editPatient(patient);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") int id) throws InvalidPatientIdException {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
