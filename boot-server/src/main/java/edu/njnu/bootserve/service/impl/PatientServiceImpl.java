package edu.njnu.bootserve.service.impl;

import edu.njnu.bootserve.dao.PatientDao;
import edu.njnu.bootserve.pojo.Patient;
import edu.njnu.bootserve.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientDao patientDao;

	@Override
	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}
}
