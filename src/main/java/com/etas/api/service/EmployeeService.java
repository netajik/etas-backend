package com.etas.api.service;

import java.util.List;
import java.util.Map;

import com.etas.api.model.Employee;

public interface EmployeeService {

	public List<Object> getEmployees();

	public Map<String, Object> getEmployee(Integer employeeId) throws Exception;

	public Employee createEmployee(Map<String, Object> payload) throws Exception;
	
	public Map<String, Object> getEmployee(Employee employee);

	public Employee updateEmployee(Map<String, Object> payload) throws Exception;

	public Employee deleteEmployee(Integer employeeId) throws Exception;
}
