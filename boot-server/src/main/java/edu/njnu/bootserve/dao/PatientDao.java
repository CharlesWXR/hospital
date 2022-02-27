package edu.njnu.bootserve.dao;

import edu.njnu.bootserve.pojo.Patient;

import java.util.List;

public interface PatientDao {
	Patient getPatientByID(int id);

	List<Patient> getAllPatients();
}
