package com.wynk.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.wynk.models.EmployeeBean;

@Repository
public class EmployeeServiceImpl implements EmployeeServices {

	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "employee";

	@Override
	public void addEmployee(EmployeeBean emp) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(emp, COLLECTION_NAME);
	}

	public List<String> getAll() {

		BasicQuery query1 = (BasicQuery) new BasicQuery("{  }");
		List<EmployeeBean> empList = mongoTemplate.find(query1, EmployeeBean.class);
		Iterator<EmployeeBean> iter = empList.iterator();
		List<String> res = new ArrayList<String>();

		Gson gson = new Gson();
		while (iter.hasNext()) {
			res.add(gson.toJson(iter.next()));
		}
		return res;
	}

	@Override
	public void deleteEmployee(EmployeeBean emp) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(emp, COLLECTION_NAME);
	}

	@Override
	public void updateEmployee(EmployeeBean emp) {
		// TODO Auto-generated method stub
		mongoTemplate.save(emp, COLLECTION_NAME);
	}

	@Override
	public EmployeeBean findById(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, EmployeeBean.class, COLLECTION_NAME);
	}
}
