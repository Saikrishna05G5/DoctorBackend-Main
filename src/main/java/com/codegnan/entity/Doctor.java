package com.codegnan.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Doctor121")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	long Salary;
	String Gender;
	int Age;
	String Specialization;
	@OneToMany(mappedBy = "doctor" ,cascade = CascadeType.ALL)
	@JsonIgnore
	List<Patient> patients;
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Doctor(int id, String name, long salary, String gender, int age, String specialization,
			List<Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		Salary = salary;
		Gender = gender;
		Age = age;
		Specialization = specialization;
		this.patients = patients;
	}
	public Doctor(String name, long salary, String gender, int age, String specialization) {
		super();
		this.name = name;
		Salary = salary;
		Gender = gender;
		Age = age;
		Specialization = specialization;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSalary() {
		return Salary;
	}
	public void setSalary(long salary) {
		Salary = salary;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getSpecialization() {
		return Specialization;
	}
	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", Salary=" + Salary + ", Gender=" + Gender + ", Age=" + Age
				+ ", Specialization=" + Specialization + ", patients=" + patients + "]";
	}
}