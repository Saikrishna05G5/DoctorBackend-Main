package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.entity.Doctor;
import com.codegnan.exception.InvalidDoctorIDException;
import com.codegnan.repo.DoctorRepo;

@Service
public class DoctorService {
	DoctorRepo doctorRepo;

	public DoctorService() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public DoctorService(DoctorRepo doctorRepo) {
		super();
		this.doctorRepo = doctorRepo;
	}
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepo.save(doctor);
	}
	public List<Doctor> findAllDoctors() throws InvalidDoctorIDException{
		return doctorRepo.findAll();
	}
	public Doctor findDoctorById(int id) throws InvalidDoctorIDException {
		Optional<Doctor> optBranch = doctorRepo.findById(id);
		if(!optBranch.isPresent()) {
			throw new InvalidDoctorIDException("Doctor ID "+id+" is not valid");
		}
		return optBranch.get();
	}
	public Doctor editDoctor(Doctor doctor) throws InvalidDoctorIDException {
		findDoctorById(doctor.getId());
		return doctorRepo.save(doctor);
	}
	public Doctor deleteDoctor(int id) throws InvalidDoctorIDException {
		Doctor doctor = findDoctorById(id);
		doctorRepo.deleteById(id);
		return doctor;
	}
}
