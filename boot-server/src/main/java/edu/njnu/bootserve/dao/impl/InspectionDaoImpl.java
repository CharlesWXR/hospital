package edu.njnu.bootserve.dao.impl;

import edu.njnu.bootserve.dao.InspectionDao;
import edu.njnu.bootserve.pojo.Inspection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class InspectionDaoImpl implements InspectionDao {
	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Inspection> getInspectionsByPatientID(int patientID) {
		List<Inspection> inspections = new ArrayList<Inspection>();
		try {
			String sql = "SELECT inspection_id FROM patient_inspection WHERE patient_id = ?";
			List<Integer> patientIDs = template.queryForList(sql, Integer.class, patientID);

			String queryForInspection = "SELECT * FROM inspection_item WHERE id = ?";
			for  (int id : patientIDs) {
				inspections.add(
						template.queryForObject(
								queryForInspection,
								new BeanPropertyRowMapper<Inspection>(Inspection.class),
								id
						)
				);
			}
		} catch (DataAccessException e) {
			log.error("Dao: failed to get inspections: patient id = {}: {}", patientID, e.getMessage());
		} finally {
			return inspections;
		}
	}
}
