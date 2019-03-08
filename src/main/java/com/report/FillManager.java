package com.report;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

import com.model.Student;

//The purpose of the FillManager is to fill the Excel report with data from the data source.
public class FillManager {
	
	public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Student> datasource) {
		  // Row offset
		  startRowIndex += 2;
		  
		  // Create cell style for the body
		  HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		  bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		  bodyCellStyle.setWrapText(true);
		  
		// Create body
		  for (int i=startRowIndex; i+startRowIndex-2< datasource.size()+2; i++) {
		   // Create a new row
		   HSSFRow row = worksheet.createRow((short) i+1);
		 
		   // Retrieve the rollno value
		   HSSFCell cell1 = row.createCell(startColIndex+0);
		   cell1.setCellValue(datasource.get(i-2).getRollno());
		   cell1.setCellStyle(bodyCellStyle);
		   
		   // Retrieve the city value
		   HSSFCell cell2 = row.createCell(startColIndex+1);
		   cell2.setCellValue(datasource.get(i-2).getCity());
		   cell2.setCellStyle(bodyCellStyle);
		 
		   // Retrieve the name value
		   HSSFCell cell3 = row.createCell(startColIndex+2);
		   cell3.setCellValue(datasource.get(i-2).getName());
		   cell3.setCellStyle(bodyCellStyle);
		 
		   // Retrieve the username value
		   HSSFCell cell4 = row.createCell(startColIndex+3);
		   cell4.setCellValue(datasource.get(i-2).getUsername());
		   cell4.setCellStyle(bodyCellStyle);
		 
		   // Retrieve the password value
		   HSSFCell cell5 = row.createCell(startColIndex+4);
		   cell5.setCellValue(datasource.get(i-2).getPassword());
		   cell5.setCellStyle(bodyCellStyle);
		   
		  }
	}

}
