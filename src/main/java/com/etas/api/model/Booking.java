package com.etas.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name="booking")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="booked_on")
	private Date bookedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="journey_on")
	private Date journeyOn;

	@Column(name="source_location")
	private String sourceLocation;

	private byte status;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

	//bi-directional many-to-one association to Cab
	@ManyToOne
	private Cab cab;

	public Booking() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBookedOn() {
		return this.bookedOn;
	}

	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}

	public Date getJourneyOn() {
		return this.journeyOn;
	}

	public void setJourneyOn(Date journeyOn) {
		this.journeyOn = journeyOn;
	}

	public String getSourceLocation() {
		return this.sourceLocation;
	}

	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Cab getCab() {
		return this.cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

}