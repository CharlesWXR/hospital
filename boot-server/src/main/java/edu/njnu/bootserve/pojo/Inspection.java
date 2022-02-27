package edu.njnu.bootserve.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Inspection {
	int id;
	String name;
	boolean starve;

	List<Inspection> dependencies;
}
