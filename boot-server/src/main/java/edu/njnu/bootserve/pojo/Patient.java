package edu.njnu.bootserve.pojo;

import lombok.Data;

@Data
public class Patient {
	int id;
	String name;
	String gender;
	int age;
	String address;
	String phone;
}
