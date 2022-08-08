package com.BugTracker.Service.Impl;

import java.util.List;

import com.BugTracker.Entity.Report;
import com.BugTracker.Repository.ReportRepository;
import com.BugTracker.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportRepository reportRepository;

	@Override
	public Report saveReport(Report report) {
		// TODO Auto-generated method stub
		return reportRepository.save(report);
	}

	@Override
	public List<Report> findAllReports() {
		// TODO Auto-generated method stub
		return reportRepository.findAll();
	}

	@Override
	public Report findReportById(Long id) {
		// TODO Auto-generated method stub
		return reportRepository.findById(id).get();
	}

}
