package com.etas.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.etas.api.dao.CabDao;
import com.etas.api.dao.EmployeeDao;
import com.etas.api.model.Cab;
import com.etas.api.model.Employee;

@Component("cabService")
@Transactional
public class CabServiceImpl implements CabService {

	Logger log = LogManager.getLogger(CabService.class);

	@Autowired
	private CabDao cabDao;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Object> getCabs() throws Exception {

		List<Cab> cabsList = cabDao.getCabs();
		List<Object> cabMaps = new ArrayList<Object>();
		for (Cab cab : cabsList) {
			cabMaps.add(getCab(cab));
		}
		return cabMaps;
	}

	Map<String, Object> getCab(Cab cab) throws Exception {
		Map<String, Object> cabMap = new HashMap<String, Object>();
		cabMap.put("id", cab.getId());
		cabMap.put("registrationNumber", cab.getRegistrationNumber());
		cabMap.put("Employee", employeeService.getEmployee(cab.getEmployee().getId()));
		cabMap.put("status", cab.getStatus());
		cabMap.put("comment", cab.getComments());
		return cabMap;
	}

	@Override
	public Map<String, Object> getCab(Integer cabId) throws Exception {
		Map<String, Object> cabMap = new HashMap<String, Object>();
		if (cabId != null) {
			log.debug("cabId received:" + cabId);
			Cab cab = cabDao.findById(cabId);
			cabMap = getCab(cab);
		} else {
			throw new Exception("requested cab id not found");
		}
		return cabMap;
	}

	@Override
	public Cab createCab(Map<String, Object> payload) throws Exception {
		Cab cab = new Cab();
		if (payload != null) {
			log.debug("new cab details received:" + payload);
			cab.setRegistrationNumber(payload.get("registrationNumber").toString());
			cab.setEmployee(employeeDao.findById((Integer) payload.get("driverId")));
			cab.setStatus( Byte.valueOf(payload.get("status").toString()));
			cab.setComments(payload.get("comments").toString());
			log.debug("new cab details saved");
			cabDao.save(cab);
		} else {
			throw new Exception("new cab details are not received");
		}
		log.debug("new cab details saved");
		return cab;
	}

	@Override
	public Cab updateCab(Map<String, Object> payload) throws Exception {
		Cab cab = new Cab();
		if (payload.get("id") != null) {
			log.debug("existing cab");
			cab = cabDao.findById((int) payload.get("id"));
			cab.setRegistrationNumber(payload.get("registrationNumber").toString());
			cab.setEmployee(employeeDao.findById((Integer) payload.get("driverId")));
			cab.setStatus( Byte.valueOf(payload.get("status").toString()));
			cab.setComments(payload.get("comments").toString());
			cab.setVacancy((Integer) payload.get("vacancy"));
			cabDao.saveOrUpdate(cab);
		} else {
			throw new Exception("cabId details not received");
		}

		return cab;
	}

	@Override
	public Cab updateCabAvailability(Integer cabId) {
		Cab cab = new Cab();
		if(cabId !=null) {
			cab = cabDao.findById(cabId);
			cab.setStatus((byte)((cab.getStatus()==1)?2:1));
			cabDao.save(cab);
		}
		return cab;
	}

	@Override
	public Cab deleteCab(Integer cabId) throws Exception {
		Cab cab = new Cab();
		log.debug("delete request in service layer");
		if (cabId != null) {
			cab = cabDao.findById(cabId);
			log.debug("cab existed");
			cabDao.delete(cab);
		} else {
			throw new Exception("requested cab id not found");
		}
		return cab;
	}

}
