package com.BugTracker.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.lowagie.text.pdf.PdfPCell;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Project pid ;
	
	private String status;
	private String start_date;
	private String end_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Project getPid() {
		return pid;
	}
	public void setPid(Project pid) {
		this.pid = pid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	@Override
	public String toString() {
		return "Report [id=" + id + ", pid=" + pid + ", status=" + status + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
	}
	public Report(Long id, Project pid, String status, String start_date, String end_date) {
		super();
		this.id = id;
		this.pid = pid;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public Report() {
		super();
		//TODO Auto-generated constructor stub
	}

	

	

		
}
