package com.etas.api.dao;

import java.util.List;

import com.etas.api.model.Cab;


public interface CabDao extends GenericDao<Cab>{
	
	public List<Cab> getCabs();
	public Cab getAvailableCab();
}
