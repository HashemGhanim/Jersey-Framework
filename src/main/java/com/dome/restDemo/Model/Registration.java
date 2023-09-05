package com.dome.restDemo.Model;

public class Registration {
	private int studentID;
	private int courseID;
	
	public Registration() {
		
	}
	public Registration(int studentID, int courseID) {
		this.studentID = studentID;
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	@Override
	public String toString() {
		return "Registration [studentID=" + studentID + ", courseID=" + courseID + "]";
	}
	
	
}
