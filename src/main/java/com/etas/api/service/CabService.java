package com.etas.api.service;

import java.util.List;
import java.util.Map;

import com.etas.api.model.Cab;

public interface CabService {
	
	public List<Object> getCabs() throws Exception;

	public Map<String, Object> getCab(Integer cabId) throws Exception;

	public Cab createCab(Map<String, Object> payload) throws Exception;

	public Cab updateCab(Map<String, Object> payload) throws Exception;
	
	public Cab updateCabAvailability(Integer cabId);

	public Cab deleteCab(Integer eabId) throws Exception;
}
