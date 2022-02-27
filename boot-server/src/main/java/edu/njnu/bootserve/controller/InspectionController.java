package edu.njnu.bootserve.controller;

import edu.njnu.bootserve.pojo.Result;
import edu.njnu.bootserve.service.InspectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/exclude/inspection")
public class InspectionController {
	@Autowired
	private InspectionService inspectionService;

	@RequestMapping(value = "/{patient_id}", method = RequestMethod.GET)
	public Result getInspections(@PathVariable("patient_id")int patient_id) {
		return Result.success(inspectionService.getInspectionsByPatientID(patient_id));
	}
}
