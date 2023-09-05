package com.dome.restDemo.DAO;

import java.util.List;

import com.dome.restDemo.Model.Course;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.Model.Student;

public interface StudentDao {
	
	public int add(Student student) throws RestDomeException; 
	public int delete(int studentID) throws RestDomeException ; 
	public Student getStudent(int studentID) throws RestDomeException; 
	public List<Student> getStudents()throws RestDomeException; 
	public List<Course> getCourses(int studentID) throws RestDomeException;
	public int update(Student student) throws RestDomeException;
	public int subscribe(int studentID, int courseID) throws RestDomeException;
	public int unSubscribe(int studentID, int courseID) throws RestDomeException;
}
