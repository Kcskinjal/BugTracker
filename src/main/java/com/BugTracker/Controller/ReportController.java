package com.BugTracker.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Report;
import com.BugTracker.Service.ProjectService;
import com.BugTracker.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ReportService reportService;

	// show Report
	@RequestMapping("/GenerateReport/{id}")
	public String AddReportDetails(@PathVariable("id") Long id, Model model) {
		Project project = projectService.getProjectById(id);
		model.addAttribute("project", project);
		return "addReport";
	}

	// Add in db report
	@PostMapping("/AddReportData")
	public String registerReport(Model model, Report report, Project project) {
		project = report.getPid();
		reportService.saveReport(report);
		return "redirect:/listReport";
	}

	@RequestMapping("/listReport")
	public String ListAllReport(Model model) {
		model.addAttribute("ReportList", reportService.findAllReports());
		return "listAllReport";
	}

	@RequestMapping("/exportToPdf/{id}")
	public void exportToPdf(HttpServletResponse response,@PathVariable("id") Long id) throws IOException, Exception
	{
		Report reportData=reportService.findReportById(id);
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=BugTracker_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		UserPDFExporter exporter = new UserPDFExporter(reportData);
        exporter.export(response);
	}

}
