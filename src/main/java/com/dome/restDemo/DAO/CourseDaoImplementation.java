package com.dome.restDemo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dome.restDemo.Model.Constants;
import com.dome.restDemo.Model.Course;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.Model.Student;


public class CourseDaoImplementation implements CourseDao{
	
	private static Connection conn = DatabaseConnection.getConnection();

	@Override
	public int add(Course course) throws RestDomeException {
		try {
			String Query = "insert into " + Constants.COURSES + " (courseID , courseName) values (? , ?)";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, course.getCourseID());
			s.setString(2, course.getCourseName());
			
			int rs = s.executeUpdate();
			
			if(rs == 0)
				throw new SQLException();
			
			return rs;
		} catch(SQLException e) {
			throw new RestDomeException("The Course ID is exist please try again.. !");
		}
	}

	@Override
	public int delete(int courseID) throws RestDomeException {
		try {
			String Query = "delete from " + Constants.COURSES + " where courseID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, courseID);
			
			int rs = s.executeUpdate();
			
			if(rs == 0)
				throw new SQLException();
			return rs;
			
		}catch(SQLException e) {
			throw new RestDomeException("The Course is already exist .. !");
		}
	}

	@Override
	public int update(Course course) throws RestDomeException {
		try {
			String Query = "update " + Constants.COURSES + " set courseName=?  where courseID =?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			
			s.setString(1, course.getCourseName());
			s.setInt(2, course.getCourseID());
			
			int rs = s.executeUpdate();
			
			if(rs == 0)
				throw new SQLException();
			return rs;
		}catch(SQLException e) {
			throw new RestDomeException("This Course is no exist .. !");
		}
	}

	@Override
	public Course getCourse(int courseID) throws RestDomeException {
		try {
			String Query = "select * from " + Constants.COURSES + " where courseID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, courseID);
			
			ResultSet rs = s.executeQuery();
			
			Course course = null;
			
			while(rs.next()){
				int courseId = rs.getInt("courseID");
				String  courseName= rs.getString("courseName");
				List <Student> students = getStudentsForOneCourse(courseID);
				
				course = new Course(courseId , courseName , students);
			}
			return course;
		} catch(SQLException e) {
			throw new RestDomeException("This Course is no exist .. !");
		}
	}

	public Course getCourseForOneStudent(int courseID) throws RestDomeException {
		try {
			String Query = "select * from " + Constants.COURSES + " where courseID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, courseID);
			
			ResultSet rs = s.executeQuery();
			
			Course course = null;
			
			while(rs.next()){
				int courseId = rs.getInt("courseID");
				String  courseName= rs.getString("courseName");
				
				course = new Course(courseId , courseName);
			}
			return course;
		} catch(SQLException e) {
			throw new RestDomeException("This Course is no exist .. !");
		}
	}
	
	@Override
	public List<Course> getCourses() throws RestDomeException {
		try {
			String Query = "select * from " + Constants.COURSES;
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			
			ResultSet rs = s.executeQuery();
			
			List <Course> courses = new ArrayList<>();
			
			while(rs.next()){
				int courseID = rs.getInt("courseID");
				String courseName = rs.getString("courseName");
				List <Student> students = getStudentsForOneCourse(courseID);
				courses.add(new Course(courseID , courseName , students)) ;
			}

			
			return courses;
		} catch(SQLException e) {
			throw new RestDomeException("There is no Courses .. !");
		}
	}
	
	

	@Override
	public List<Student> getStudentsForOneCourse(int courseID) throws RestDomeException {
		try {
			
			StudentDaoImplementation studentDao = new StudentDaoImplementation();
			
			String Query = "select studentID from " + Constants.REGISTRATIONS + " where courseID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, courseID);
			
			ResultSet rs = s.executeQuery();
			
			
			List<Student> students = new ArrayList<>();
			
			while(rs.next()) {
				students.add(studentDao.getStudentForOneCourse(rs.getInt("studentID")));
			}

			
			return students;
			
		} catch(SQLException e) {
			throw new RestDomeException("There is no Students in this Course .. !");
		}
	}
	
}
