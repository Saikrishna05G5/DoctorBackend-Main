package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.entity.Doctor;
import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidPatientIdException;
import com.codegnan.repo.PatientRepo;
@Service
public class PatientService {
	PatientRepo patientRepo;
	public PatientService() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public PatientService(PatientRepo patientRepo) {
		super();
		this.patientRepo = patientRepo;
	}
	public Patient savePatient(Patient patient) {
		return patientRepo.save(patient);
	}
	public List<Patient> findAllPatients(){
		return patientRepo.findAll();
	}
	public Patient findPatientById(int id) throws InvalidPatientIdException {
		Optional<Patient> optPatient = patientRepo.findById(id);
		if(optPatient.isEmpty()) {
			throw new InvalidPatientIdException("Patient id is not valid : "+id);
		}
		return optPatient.get();
	}
	public List<Patient> findPatientByDoctor(Doctor doctor){
		return patientRepo.findAllByDoctor(doctor);
	}
	public Patient editPatient(Patient patient) throws InvalidPatientIdException {
		findPatientById(patient.getId());
		return patientRepo.save(patient);
	}
	public Patient deletePatient(int id) throws InvalidPatientIdException {
		Patient patient = findPatientById(id);
		patientRepo.deleteById(id);
		return patient;
	}

}
