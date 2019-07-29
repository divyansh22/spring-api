package com.wynk.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.validator.constraints.NotBlank;

@Document(collection="employee")
public class EmployeeBean {
	
	@Id
	private String id;
	@NotBlank(message="Name cannot be blank")
	private String name;
	@NotBlank(message="Department cannot be blank")	
	private String department;
	@NotBlank(message="Email cannot be blank")	
	private String email;
	
	private static final AtomicLong counter = new AtomicLong(10);
	
	public EmployeeBean(String name, String department, String email) {
		this.id = Long.toString(counter.incrementAndGet());
		this.name = name;
		this.department = department;
		this.email = email;
	}
	
	public EmployeeBean() {
		this.id = Long.toString(counter.incrementAndGet());
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeBean [id=" + id + ", name=" + name + ", department=" + department + ", email=" + email + "]";
	}
	
}
