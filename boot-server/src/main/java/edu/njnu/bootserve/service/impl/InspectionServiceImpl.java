package edu.njnu.bootserve.service.impl;

import edu.njnu.bootserve.dao.InspectionDao;
import edu.njnu.bootserve.pojo.Inspection;
import edu.njnu.bootserve.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionServiceImpl implements InspectionService {
	@Autowired
	InspectionDao inspectionDao;

	@Override
	public List<Inspection> getInspectionsByPatientID(int patientID) {
		return inspectionDao.getInspectionsByPatientID(patientID);
	}
}
