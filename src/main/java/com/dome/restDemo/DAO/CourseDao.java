package com.dome.restDemo.DAO;

import java.util.List;

import com.dome.restDemo.Model.Course;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.Model.Student;


public interface CourseDao {
	
	public int add(Course course) throws RestDomeException;
	public int delete(int courseID) throws RestDomeException;
	public int update(Course course) throws RestDomeException;
	public Course getCourse(int courseID) throws RestDomeException;
	public List<Course> getCourses() throws RestDomeException;
	public List<Student> getStudentsForOneCourse(int courseID) throws RestDomeException;
}
