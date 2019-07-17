package com.etas.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etas.api.config.Mappings;
import com.etas.api.model.Employee;
import com.etas.api.service.EmployeeService;

@RestController
public class EmployeeController {
	
	static Logger log = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = Mappings.GET_EMPLOYEES, method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployees() {
		log.debug("Request received for:" + Mappings.GET_EMPLOYEES);
		List<Object> employees = employeeService.getEmployees();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("employees", employees);
		log.debug("sending response having list of employees");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_EMPLOYEE, method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployee(@PathVariable Integer employeeId) {
		log.debug("Request for : " + Mappings.GET_EMPLOYEE);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Map<String, Object> employee = employeeService.getEmployee(employeeId);
			response.put("employee", employee);
		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("sending respose having employee of requested employeeId :"+employeeId);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.CREATE_EMPLOYEE, method = RequestMethod.POST)
	public ResponseEntity<Object> createEmployee(@RequestBody Map<String, Object> payload) {
		log.debug("Request received for:" + Mappings.CREATE_EMPLOYEE);
		log.debug("new employee details:" + payload);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Employee employee = employeeService.createEmployee(payload);
			log.debug("new employeeId: " + employee.getId());
			response.put("employee", employee);
		} catch (Exception e) {
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		log.debug("sending response having list of employees");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.UPDATE_EMPLOYEE, method = RequestMethod.PUT)
	public ResponseEntity<Object> updateEmployee(@RequestBody Map<String, Object> payload) {
		log.debug("Request for : " + Mappings.DELETE_EMPLOYEE);
		log.debug("update content" + payload);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Employee employee = employeeService.updateEmployee(payload);
			response.put("employee", employee);
		} catch (Exception e) {
			// response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("sending respose for employee update ");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_EMPLOYEE, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteEmployee(@PathVariable Integer employeeId) {
		log.debug("Request for : " + Mappings.DELETE_EMPLOYEE);
		log.debug("request Id:"+employeeId);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Employee employee = employeeService.deleteEmployee(employeeId);
			response.put("employee", employee);
		} catch (Exception e) {
			// response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("deleted employee object corresponding requested Id: " + employeeId);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
