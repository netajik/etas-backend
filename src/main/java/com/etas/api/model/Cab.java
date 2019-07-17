package com.etas.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cab database table.
 * 
 */
@Entity
@Table(name="cab")
@NamedQuery(name="Cab.findAll", query="SELECT c FROM Cab c")
public class Cab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comments;

	@Column(name="registration_number")
	private String registrationNumber;

	private byte status;
	
	private int vacancy = 0;
	
	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="cab")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="driver_id")
	private Employee employee;

	public Cab() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
	public int getVacancy() {
		return this.vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setCab(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setCab(null);

		return booking;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}