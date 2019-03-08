package com.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.DataNotFoundException;
import com.demo.exception.ErrorMessage;
import com.model.Student;
import com.service.DownloadService;
import com.service.StudentService;

@RestController
@RequestMapping("/")
public class StudentController {

	@Autowired
	StudentService service;
	
	@Autowired
	DownloadService downloadService;
	
	@RequestMapping(value="/student/verifylogin/{username}/{password}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean checkUserLogin(@PathVariable("username")String un,@PathVariable("password")String pwd) {
		return service.checkUserLogin(un, pwd);
	}

	@RequestMapping(value = "/student/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Student> getAllStudents() {
		return service.getAllStudents();
	}

	@RequestMapping(value = "/student/get/{rollno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void getStudentByRollno(@PathVariable("rollno")Integer rollno) {
    	this.service.getStudentByRollno(rollno);
	}
	
    @RequestMapping(value="/student/addstudent", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addStudent(@RequestBody Student stu) throws URISyntaxException{
		boolean flag=service.addStudent(stu);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(new URI("/student/addstudent"));
		return new ResponseEntity<Boolean>(flag, header, HttpStatus.CREATED);
	}
    
    /*@RequestMapping(value="/student/deletestudent/{rollno}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody boolean deleteStudent(@PathVariable("rollno") int rollno){
    	return service.removeStudent(rollno);
    	
    }*/
    
    @RequestMapping(value="/student/updatestudent", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void updateStudent(@RequestBody Student stu,Integer rollno) {
    	 this.service.updateStudent(stu);
    }
    
    @ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), HttpStatus.PRECONDITION_FAILED.value(), "data not found for the message plz send avalid id");
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}
    
    @RequestMapping(value="/student/deletestudent/{rollno}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody void removeStudent(@PathVariable("rollno")Integer rollno) {
    	this.service.removeStudent(rollno);
    }
    
    @RequestMapping(value="/student/download", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void getXLS(HttpServletResponse res, Model model) throws IOException {
    	
    	downloadService.downloadXLS(res);
    }

}
