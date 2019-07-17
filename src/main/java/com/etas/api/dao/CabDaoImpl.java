package com.etas.api.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.etas.api.model.Cab;

@Component("cabDao")
public class CabDaoImpl extends AbstractGenericDao<Cab> implements CabDao{
	
	@Override
	public List<Cab> getCabs() {
		String queryString = "From Cab";
		Query query = getSession().createQuery(queryString);
		return query.getResultList();
	}

	@Override
	public Cab getAvailableCab() {
		String queryString = "From Cab where status = 1 AND vacancy >=1";
		Query query = getSession().createQuery(queryString);
		query.setMaxResults(1);
		Cab cab = null;
		try {
			cab = (Cab) query.getSingleResult();
		} catch(NoResultException e) {
			return cab;
		}
		return cab;
	}
}
