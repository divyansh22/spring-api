package com.wynk.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.google.gson.Gson;
import com.wynk.models.EmployeeBean;
import com.wynk.services.EmployeeServices;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeServices employeeServices;
	private Gson gson = new Gson();
	

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> list() {
		List<String> response = employeeServices.getAll();

		if (response == null) {
			return new ResponseEntity<String>("{\"message\" : \"Something went wrong!\"}", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> addEmployee(@RequestParam("name") String name,
			@RequestParam("department") String department,
			@RequestParam("email") String email) {
		JSONObject response = new JSONObject();
		try {
			EmployeeBean empObj = new EmployeeBean(name, department, email);
			employeeServices.addEmployee(empObj);
			response.put("inserted", "true");
		} catch(Exception ex) {
			response.put("error", ex.toString());
		}
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> view(@RequestParam("id") String id) {
		
		EmployeeBean emp = employeeServices.findById(id);
		String empJson = gson.toJson(emp);
		
		return new ResponseEntity<String>(empJson, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces="application/json")
	public ResponseEntity<String> updateEmployee(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("department") String department,
			@RequestParam("email") String email) {
		
		JSONObject response = new JSONObject();
		try {
			EmployeeBean emp = employeeServices.findById(id);
			emp.setName(name);
			emp.setDepartment(department);
			emp.setEmail(email);
			employeeServices.updateEmployee(emp);
			response.put("updated", "true");
		} catch(Exception ex) {
			response.put("error", ex.toString());
		}
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<String> delete(@RequestParam("id") String id) {
		JSONObject response = new JSONObject();
		try {
			EmployeeBean emp = employeeServices.findById(id);
			employeeServices.deleteEmployee(emp);
			response.put("deleted", "true");
		}
		catch(Exception ex) {
			response.put("error", ex.toString());
		}
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
	
}
