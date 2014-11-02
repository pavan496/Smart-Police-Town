package com.dh.spt.db.model;

// Generated 2 Nov, 2014 9:54:33 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ComplaintTypes generated by hbm2java
 */
@Entity
@Table(name = "COMPLAINT_TYPES")
public class ComplaintTypes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8545854731945761127L;
	private String complaintTypeCode;
	private String complaintTypeName;

	public ComplaintTypes() {
	}

	public ComplaintTypes(String complaintTypeCode) {
		this.complaintTypeCode = complaintTypeCode;
	}

	public ComplaintTypes(String complaintTypeCode, String complaintTypeName) {
		this.complaintTypeCode = complaintTypeCode;
		this.complaintTypeName = complaintTypeName;
	}

	@Id
	@Column(name = "COMPLAINT_TYPE_CODE", unique = true, nullable = false, length = 20)
	public String getComplaintTypeCode() {
		return this.complaintTypeCode;
	}

	public void setComplaintTypeCode(String complaintTypeCode) {
		this.complaintTypeCode = complaintTypeCode;
	}

	@Column(name = "COMPLAINT_TYPE_NAME", length = 50)
	public String getComplaintTypeName() {
		return this.complaintTypeName;
	}

	public void setComplaintTypeName(String complaintTypeName) {
		this.complaintTypeName = complaintTypeName;
	}

}
