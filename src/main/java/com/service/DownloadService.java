package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Student;
import com.report.FillManager;
import com.report.Layouter;
import com.report.Writer;


@Service
@Transactional
public class DownloadService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void downloadXLS(HttpServletResponse res) throws IOException {
		
		// 1. Create new workbook
		  HSSFWorkbook workbook = new HSSFWorkbook();
		  
		// 2. Create new worksheet
		  HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
		   
		// 3. Define starting indices for rows and columns
		  int startRowIndex = 0;
		  int startColIndex = 0;
		  
		// 4. Build layout 
		  // Build title, date, and column headers
		  Layouter.buildReport(worksheet, startRowIndex, startColIndex);
		 
		  
		// 5. Fill report
		  FillManager.fillReport(worksheet, startRowIndex, startColIndex, getDatasource());

		  
		// 6. Set the response properties
		  String fileName = "Report.xls";
		  res.setHeader("Content-Disposition", "inline; filename=" + fileName);
		  // Make sure to set the correct content type
		  res.setContentType("application/vnd.ms-excel");
		  
		  
		//7. Write to the output stream
		  Writer.write(res, worksheet);
		  
		  /*FileOutputStream out = new FileOutputStream(new File("D:\\reports.xlsx"));
		  workbook.write(out);
		  out.close();*/
		  System.out.println("Sheet written successfully");
		  
		

	}
	
	/**
	  * Retrieves the data as as simple Java List.
	  * The data is retrieved from a Hibernate HQL query.
	  */
	 @SuppressWarnings("unchecked")
	 private List<Student> getDatasource() {
	   
	      // Retrieve session
	  Session session = sessionFactory.getCurrentSession();
	  // Create query for retrieving products
	  Query query = session.createQuery("FROM Student");
	  // Execute query
	  List<Student> result = query.list();
	   
	  // Return the datasource
	  return result;
	 }

}
