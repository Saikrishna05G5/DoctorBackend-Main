package com.codegnan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	int Weight;
	String Gender;
	int Age;
	String Disease;
	@ManyToOne
	@JoinColumn(name="doctor_id")
	Doctor doctor;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(int id, String name, int weight, String gender, int age, String disease, Doctor doctor) {
		super();
		this.id = id;
		this.name = name;
		Weight = weight;
		Gender = gender;
		Age = age;
		Disease = disease;
		this.doctor = doctor;
	}
	public Patient(String name, int weight, String gender, int age, String disease, Doctor doctor) {
		super();
		this.name = name;
		Weight = weight;
		Gender = gender;
		Age = age;
		Disease = disease;
		this.doctor = doctor;
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
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
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
	public String getDisease() {
		return Disease;
	}
	public void setDisease(String disease) {
		Disease = disease;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", Weight=" + Weight + ", Gender=" + Gender + ", Age=" + Age
				+ ", Disease=" + Disease + ", doctor=" + doctor + "]";
	}
}
