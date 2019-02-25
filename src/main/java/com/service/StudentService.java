package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.StudentDao;
import com.demo.exception.DataNotFoundException;
import com.model.Student;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	StudentDao dao;
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public List<Student> getAllStudents(){
		List<Student> list=dao.getAllStudent();
		return list;
		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public List<Student> getAllStudentsById(int id){
		List<Student> list=dao.getStudentById(id);
		if(list.size()==0) {
			throw new DataNotFoundException("Student with"+id+"not found");
		}
		return list;
	}
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public boolean addStudent(Student stu) {
		Student st=new Student();
		st.setCity(stu.getCity());
		st.setName(stu.getName());
		st.setPassword(stu.getPassword());
		st.setUsername(stu.getUsername());
		boolean flag=dao.addStudent(st);
		return flag;
		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public boolean deleteStudentById(int id) {
		/*return dao.deleteStudentById(id);*/
		return this.dao.deleteStudentById(id);
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public boolean updateStudentById(int id,Student stu) {
		boolean flag=dao.updateStudentById(id, stu);
		return flag;
		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public boolean checkUserLogin(String un, String pwd) {
		return dao.checkUserLogin(un, pwd);
	}

}
