package com.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;

//The purpose of the Writer is to write the "exported" worksheet to the output stream. Once the document has been written to the stream, the user will receive the document ready to be downloaded.

public class Writer {
	
	
	public static void write(HttpServletResponse res, HSSFSheet worksheet) {
		try {
			/*// Retrieve the output stream
			ServletOutputStream outputStream = res.getOutputStream();
			// Write to the output stream
			worksheet.getWorkbook().write(outputStream);
			//Flush the stream
			outputStream.flush();*/
			
			FileOutputStream out = new FileOutputStream(new File("D:\\reports.xls"));
			  worksheet.getWorkbook().write(out);
			  out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
