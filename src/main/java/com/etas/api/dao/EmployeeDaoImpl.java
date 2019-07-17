package com.etas.api.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.etas.api.model.Employee;

@Component("employeeDao")
public class EmployeeDaoImpl extends AbstractGenericDao<Employee> implements EmployeeDao  {

	@Override
	public List<Employee> getEmployees() {
		String queryString = "From Employee";
		Query query = getSession().createQuery(queryString, Employee.class);
		return query.getResultList();
	}
	
}
