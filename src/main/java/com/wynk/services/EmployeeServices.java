package com.wynk.services;

import java.util.List;
import com.wynk.models.EmployeeBean;

public interface EmployeeServices {
	
	public void addEmployee(EmployeeBean emp);
	public List<String> getAll();
	public EmployeeBean findById(String id);
	public void deleteEmployee(EmployeeBean emp);
	public void updateEmployee(EmployeeBean emp);
}