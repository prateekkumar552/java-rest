package com.dao;

import java.util.List;

import com.model.Student;

public interface StudentDao {
	
	public List<Student> getAllStudent();
	public List<Student> getStudentById(int id);
	public boolean addStudent(Student stu);
	public boolean deleteStudentById(int id);
	/*public void deleteStudent(int rollno);*/
	public boolean updateStudentById(int id, Student stu);
	public boolean checkUserLogin(String un,String pwd);
	

}
