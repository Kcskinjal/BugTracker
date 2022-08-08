package com.BugTracker.Service;

import java.util.List;

import com.BugTracker.Entity.Report;

import org.springframework.stereotype.Service;

@Service
public interface ReportService {
	Report saveReport(Report report);
	
	List<Report> findAllReports();
	
	Report findReportById(Long id);
}
