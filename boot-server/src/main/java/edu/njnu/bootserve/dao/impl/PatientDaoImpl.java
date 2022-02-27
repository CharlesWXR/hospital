package edu.njnu.bootserve.dao.impl;

import edu.njnu.bootserve.dao.PatientDao;
import edu.njnu.bootserve.pojo.Patient;
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
public class PatientDaoImpl implements PatientDao {
	@Autowired
	JdbcTemplate template;

	@Override
	public Patient getPatientByID(int id) {
		try {
			String sql = "SELECT * FROM patient WHERE id = ?";
			return template.queryForObject(sql, new BeanPropertyRowMapper<Patient>(Patient.class), id);
		} catch (DataAccessException e) {
			log.error("Dao: get patient failed with id={}: {}", id, e.getMessage());
			return null;
		}
	}

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> res = new ArrayList<>();
		try {
			String sql = "SELECT * FROM patient";
			res = template.query(sql, new BeanPropertyRowMapper<Patient>(Patient.class));
		} catch (DataAccessException e) {
			log.error("Dao: failed to get all patients: {}", e.getMessage());
		} finally {
			return res;
		}
	}
}
