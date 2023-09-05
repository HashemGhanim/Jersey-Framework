package com.dome.restDemo.services;


import java.util.List;

import com.dome.restDemo.DAO.CourseDaoImplementation;
import com.dome.restDemo.Model.Course;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.Model.Student;


public class CourseServices {
	private CourseDaoImplementation courseDao;
	
	public CourseServices() {
		courseDao = new CourseDaoImplementation();
	}
	
	public Course getCourse(int courseID) throws RestDomeException{
		Course s = courseDao.getCourse(courseID);
		return s;
	}
	
	public int updateCourse(Course course) throws RestDomeException {
		
		Validation.isValidID(course.getCourseID());
		Validation.isValidCourseName(course.getCourseName());
		
		int rs = courseDao.update(course);
		return rs;
	}
	
	public int setCourse(Course course) throws RestDomeException {
		
		Validation.isValidID(course.getCourseID());
		Validation.isValidCourseName(course.getCourseName());
		
		return courseDao.add(course);
	}
	
	public int delCourse(int courseID) throws RestDomeException {
		Validation.isValidID(courseID);
		
		int s = courseDao.delete(courseID);

		return s;
	}
	
	public List<Course> getCourses() throws RestDomeException{
		
		return courseDao.getCourses();
	}
	
	public List<Student> getStudentsForOneCourse(int courseID) throws RestDomeException{
		Validation.isValidID(courseID);
		
		return courseDao.getStudentsForOneCourse(courseID);
	}
}
