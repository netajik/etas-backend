package com.etas.api.dao;

import java.util.List;

import com.etas.api.model.Employee;

public interface EmployeeDao extends GenericDao<Employee> {
	public List<Employee> getEmployees();
}
