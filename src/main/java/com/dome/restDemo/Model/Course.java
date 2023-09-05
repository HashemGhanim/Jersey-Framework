package com.dome.restDemo.Model;

import java.util.List;

public class Course {
	
	private int courseID;
	private String courseName;
	private List<Student> students = null;
	
	public Course() {
		
	}

	
	public Course(int courseID, String courseName) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
	}


	public Course(int courseID, String courseName, List<Student> students) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.students = students;
	}

	
	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseName=" + courseName + ", students=" + students + "]";
	}

	
	
}

