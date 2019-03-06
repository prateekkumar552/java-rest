package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	
	@Autowired
	HibernateTemplate temp;
	
		

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		List<Student> stulist=temp.execute(new HibernateCallback<List<Student>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Criteria ct=session.createCriteria(Student.class);
				return (List<Student>)ct.list();
			}
		});
		return stulist;
	}

	/*@Override
	public List<Student> getStudentById(final int id) {
		// TODO Auto-generated method stubs
		
		List<Student> stulist=temp.execute(new HibernateCallback<List<Student>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Criteria ct=session.createCriteria(Student.class);
				ct.add(Restrictions.eq("id", id));
				return (List<Student>)ct.list();
			}
		});
		return stulist;
	}
*/
	@Override
	public boolean addStudent(Student stu) {
		// TODO Auto-generated method stub
		try {
			temp.save(stu);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	/*@Override
	public boolean deleteStudentById(int id) {
		// TODO Auto-generated method stub
		Student stu=temp.load(Student.class, id);
		try {
			temp.delete(stu);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}*/

	

	@Override
	public boolean checkUserLogin(String un, String pwd) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Object[]> listlog=(List<Object[]>) temp.find("select username,password from Student where username=? and password=?", new Object[]{un,pwd});
		if(listlog.size() == 0) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public void deleteStudent(Integer rollno) {
		// TODO Auto-generated method stub
		Student st=(Student)temp.load(Student.class, new Integer(rollno));
		if(null != st) {
			temp.delete(st);
		}
		/*List<Object[]> listlog=(List<Object[]>) temp.find("delete from Student where id=?", new Object[] {rollno});
		if(listlog.size() == 0) {
			return false;
		}else {
			return true;
		}*/
	}

	@Override
	public Student getStudentByRollno(int rollno) {
		// TODO Auto-generated method stub
		Student st=temp.load(Student.class, new Integer(rollno));
		
		return st;
	}

	@Override
	public void updateStudent(Student stu) {
		// TODO Auto-generated method stub
		
		String sql="update Student set name="+stu.getName()+",city="+stu.getCity();
		temp.update(sql);
		
		
	}

}
