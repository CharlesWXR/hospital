package edu.njnu.bootserve.dao;

import edu.njnu.bootserve.pojo.Inspection;

import java.util.List;

public interface InspectionDao {
	List<Inspection> getInspectionsByPatientID(int patientID);
}
