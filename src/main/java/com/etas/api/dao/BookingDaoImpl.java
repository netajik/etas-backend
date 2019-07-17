package com.etas.api.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.etas.api.model.Booking;

@Component("bookingDao")
public class BookingDaoImpl extends AbstractGenericDao<Booking> implements BookingDao {


}
