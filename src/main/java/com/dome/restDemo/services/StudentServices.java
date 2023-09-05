package com.dome.restDemo.services;


import java.util.List;

import com.dome.restDemo.DAO.StudentDao;
import com.dome.restDemo.DAO.StudentDaoImplementation;
import com.dome.restDemo.Model.Course;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.Model.Student;


public class StudentServices {
	private StudentDao studentDao;
	
	public StudentServices() {
		studentDao = new StudentDaoImplementation();
	}
	
	public Student getStudent(int studentID) throws RestDomeException {
		Validation.isValidID(studentID);
		
		Student student = studentDao.getStudent(studentID);
		return student;
	}
	
	
	public int updateStudent(Student student) throws RestDomeException{
		
		Validation.isValidID(student.getStudentID());
		Validation.isValidFirstName(student.getFirstName());
		Validation.isValidEmail(student.getEmail());
		Validation.isValidPhone(student.getPhone());
		
		int rs = studentDao.update(student);
		return rs;
	}
	
	
	public int setStudent(Student student) throws RestDomeException {
		Validation.isValidFirstName(student.getFirstName());
		Validation.isValidEmail(student.getEmail());
		Validation.isValidPhone(student.getPhone());
		
		return studentDao.add(student);
	}
	
	
	public int delStudent(int studentID) throws RestDomeException{
		Validation.isValidID(studentID);
		
		int s = studentDao.delete(studentID);
		
		return s;
	}

	public List<Student> getStudents() throws RestDomeException{
		return studentDao.getStudents();
	}
	
	public int subscribe(int studentID, int courseID) throws RestDomeException{
		Validation.isValidID(studentID);
		Validation.isValidID(courseID);
		
		return studentDao.subscribe(studentID, courseID);
	}
	
	public int unSubscribe(int studentID, int courseID) throws RestDomeException{
		Validation.isValidID(studentID);
		Validation.isValidID(courseID);
		
		return studentDao.unSubscribe(studentID, courseID);
	}
	
	public List<Course> getCourses(int studentID) throws RestDomeException{
		Validation.isValidID(studentID);
		
		return studentDao.getCourses(studentID);
	}
}
