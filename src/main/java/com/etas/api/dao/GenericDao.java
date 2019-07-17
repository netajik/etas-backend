package com.etas.api.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface to provide common DAO methods
 * 
 */

public interface GenericDao<E> {

	/**
	 * 
	 * @param entity: entity to save
	 * @return Identifier of saved entity
	 */
	Serializable save(E entity);

	/**
	 * 
	 * @param entity:entity to save or update
	 * @return
	 */

	public void saveOrUpdate(E entity);

	/**
	 * 
	 * @param entity: entity to delete
	 */
	void delete(E entity);

	/**
	 * get only certain properties by id
	 * 
	 * @param id
	 * @param properties
	 * @return
	 */

	Object findById(Serializable id, String properties, String identifier);

	/**
	 * 
	 * @param properties
	 * @param Query
	 * @return
	 */
	Object findByQuery(String properties, String Query);

	/**
	 * 
	 * @param properties
	 * @return
	 */
	List<Object> findAll(String properties);

	/**
	 * Find by primary key
	 * 
	 * @param id
	 * @return unique entity
	 */
	E findById(Serializable id);

	E merge(E e);

	/**
	 * 
	 * @param id
	 * @param fileds
	 * @param identifier
	 * @param userId
	 * @param subQuery
	 */

	void update(Serializable id, Map<String, String> fileds, String identifier, int userId, String subQuery);

	/**
	 * Clear session
	 */

	void clear();

	/**
	 * Flush session
	 */
	void flush();

	List<E> findAll();

}

