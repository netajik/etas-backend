package com.etas.api.config;

public class Mappings {

	public static final String GET_EMPLOYEES = "/employees";
	public static final String GET_EMPLOYEE = "/employee/{employeeId}";
	public static final String CREATE_EMPLOYEE = "/employee";
	public static final String UPDATE_EMPLOYEE = "/employee";
	public static final String DELETE_EMPLOYEE = "/employee/{employeeId}";
	
	public static final String GET_CABS = "/cabs";
	public static final String GET_CAB = "/cabs/{cabId}";
	public static final String CREATE_CAB = "/cabs";
	public static final String UPDATE_CAB = "/cabs";
	public static final String UPDATE_CAB_AVAILABLE = "/cabs/{cabId}/available";
	public static final String UPDATE_CAB_UNAVAILABLE = "/cabs/{cabId}/unavailable";
	public static final String DELETE_CAB = "/cabs/{cabId}";
	
	public static final String BOOKING_REQUEST = "/request";
	public static final String BOOKING_REQUEST_STATUS = "/request/{requestId}";
	public static final String BOOKING_STATUS = "/booking/{bookingId}";
	
	
}
