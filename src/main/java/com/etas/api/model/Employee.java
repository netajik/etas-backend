package com.etas.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String address;

	private String designation;

	private String email;

	@Column(name="full_name")
	private String fullName;

	@Temporal(TemporalType.DATE)
	@Column(name="joining_date")
	private Date joiningDate;

	private String phone;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="employee")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Cab
	@OneToMany(mappedBy="employee")
	private List<Cab> cabs;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getJoiningDate() {
		return this.joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setEmployee(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setEmployee(null);

		return booking;
	}

	public List<Cab> getCabs() {
		return this.cabs;
	}

	public void setCabs(List<Cab> cabs) {
		this.cabs = cabs;
	}

	public Cab addCab(Cab cab) {
		getCabs().add(cab);
		cab.setEmployee(this);

		return cab;
	}

	public Cab removeCab(Cab cab) {
		getCabs().remove(cab);
		cab.setEmployee(null);

		return cab;
	}

}