package com.etas.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.etas.api.dao.EmployeeDao;
import com.etas.api.model.Employee;

@Component("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	Logger log = LogManager.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Object> getEmployees() {

		List<Employee> employeesList = employeeDao.getEmployees();
		List<Object> employeeMaps = new ArrayList<Object>();
		for (Employee employee : employeesList) {
			employeeMaps.add(getEmployee(employee));
		}
		return employeeMaps;
	}
	
	@Override
	public Map<String, Object> getEmployee(Employee employee) {
		Map<String, Object> employeeMap = new HashMap<String, Object>();
		employeeMap.put("id", employee.getId());
		employeeMap.put("fullName", employee.getFullName());
		employeeMap.put("designation", employee.getDesignation());
		employeeMap.put("joiningDate", employee.getJoiningDate());
		employeeMap.put("phone", employee.getPhone());
		employeeMap.put("address", employee.getAddress());
		return employeeMap;
	}

	@Override
	public Map<String, Object> getEmployee(Integer employeeId) throws Exception {
		Map<String, Object> employeeMap = new HashMap<String, Object>();
		if (employeeId != null) {
			log.debug("employeeId received:" + employeeId);
			Employee employee = employeeDao.findById(employeeId);
			employeeMap = getEmployee(employee);
		} else {
			throw new Exception("requested employee id not found");
		}
		return employeeMap;
	}

	@Override
	public Employee createEmployee(Map<String, Object> payload) throws Exception {
		Employee employee = new Employee();
		if (payload != null) {
			log.debug("new employee details received:" + payload);
			employee.setFullName(payload.get("fullName").toString());
			employee.setDesignation(payload.get("designation").toString());
			employee.setJoiningDate(new Date((long) payload.get("joinDate")));
			employee.setPhone(payload.get("phone").toString());
			employee.setEmail(payload.get("email").toString());
			employee.setAddress(payload.get("address").toString());
			employeeDao.save(employee);
		} else {
			throw new Exception("new employee details are not received");
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(Map<String, Object> payload) throws Exception {
		Employee employee = new Employee();
		if (payload.get("id") != null) {
			log.debug("existing employee");
			employee = employeeDao.findById((int) payload.get("id"));
			employee.setFullName(payload.get("fullName").toString());
			employee.setDesignation(payload.get("designation").toString());
			employee.setJoiningDate(new Date((long) payload.get("joinDate")));
			employee.setPhone(payload.get("phone").toString());
			employee.setEmail(payload.get("email").toString());
			employee.setAddress(payload.get("address").toString());
			employeeDao.saveOrUpdate(employee);
		} else {
			throw new Exception("exsting employee details not received");
		}

		return employee;
	}

	@Override
	public Employee deleteEmployee(Integer employeeId) throws Exception {
		Employee employee = new Employee();
		log.debug("delete request in service layer");
		if (employeeId != null) {
			employee = employeeDao.findById(employeeId);
			log.debug("employee existed");
			employeeDao.delete(employee);
		} else {
			throw new Exception("requested employee id not found");
		}
		return employee;
	}

}
