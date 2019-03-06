package com.dao;

import java.util.List;

import com.model.Student;

public interface StudentDao {
	
	public List<Student> getAllStudent();
	public boolean addStudent(Student stu);
	public Student getStudentByRollno(int rollno);
	public void deleteStudent(Integer rollno);
	public void updateStudent(Student stu);
	public boolean checkUserLogin(String un,String pwd);
	

}
