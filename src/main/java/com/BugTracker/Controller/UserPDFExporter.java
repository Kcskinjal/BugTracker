package com.BugTracker.Controller;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.BugTracker.Entity.Report;
import com.BugTracker.Entity.User;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UserPDFExporter {

	private Report reportData;

	public UserPDFExporter(Report reportData) {
		this.reportData = reportData;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("id", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Projectname", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("start_date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("end_date", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		table.addCell(String.valueOf(reportData.getId()));
		table.addCell(reportData.getPid().getProject_name());
		table.addCell(reportData.getStatus());
		table.addCell(reportData.getStart_date());
		table.addCell(reportData.getEnd_date());
	}

	public void export(HttpServletResponse response) throws Exception, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Project Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}

}
