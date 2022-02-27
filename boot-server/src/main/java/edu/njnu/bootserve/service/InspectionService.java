package edu.njnu.bootserve.service;

import edu.njnu.bootserve.pojo.Inspection;

import java.util.List;

public interface InspectionService {
	List<Inspection> getInspectionsByPatientID(int patientID);
}
