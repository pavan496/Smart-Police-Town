package com.dh.spt.db.model;

// Generated 6 Nov, 2014 1:26:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Complaints generated by hbm2java
 */
public class Complaints implements java.io.Serializable {

	private Integer id;
	private ComplaintTypes complaintTypes;
	private String incidentId;
	private String incidentLocataion;
	private Date incidentTime;
	private String summary;
	private String reporterName;
	private String reporterContactNo;
	private String reporterEmailAddress;
	private Date reportedTime;
	private String reportedLocation;

	public Complaints() {
	}

	public Complaints(Date incidentTime, Date reportedTime) {
		this.incidentTime = incidentTime;
		this.reportedTime = reportedTime;
	}

	public Complaints(ComplaintTypes complaintTypes, String incidentId,
			String incidentLocataion, Date incidentTime, String summary,
			String reporterName, String reporterContactNo,
			String reporterEmailAddress, Date reportedTime,
			String reportedLocation) {
		this.complaintTypes = complaintTypes;
		this.incidentId = incidentId;
		this.incidentLocataion = incidentLocataion;
		this.incidentTime = incidentTime;
		this.summary = summary;
		this.reporterName = reporterName;
		this.reporterContactNo = reporterContactNo;
		this.reporterEmailAddress = reporterEmailAddress;
		this.reportedTime = reportedTime;
		this.reportedLocation = reportedLocation;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ComplaintTypes getComplaintTypes() {
		return this.complaintTypes;
	}

	public void setComplaintTypes(ComplaintTypes complaintTypes) {
		this.complaintTypes = complaintTypes;
	}

	public String getIncidentId() {
		return this.incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getIncidentLocataion() {
		return this.incidentLocataion;
	}

	public void setIncidentLocataion(String incidentLocataion) {
		this.incidentLocataion = incidentLocataion;
	}

	public Date getIncidentTime() {
		return this.incidentTime;
	}

	public void setIncidentTime(Date incidentTime) {
		this.incidentTime = incidentTime;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getReporterName() {
		return this.reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getReporterContactNo() {
		return this.reporterContactNo;
	}

	public void setReporterContactNo(String reporterContactNo) {
		this.reporterContactNo = reporterContactNo;
	}

	public String getReporterEmailAddress() {
		return this.reporterEmailAddress;
	}

	public void setReporterEmailAddress(String reporterEmailAddress) {
		this.reporterEmailAddress = reporterEmailAddress;
	}

	public Date getReportedTime() {
		return this.reportedTime;
	}

	public void setReportedTime(Date reportedTime) {
		this.reportedTime = reportedTime;
	}

	public String getReportedLocation() {
		return this.reportedLocation;
	}

	public void setReportedLocation(String reportedLocation) {
		this.reportedLocation = reportedLocation;
	}

}
