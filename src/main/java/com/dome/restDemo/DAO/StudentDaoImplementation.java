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


public class StudentDaoImplementation implements StudentDao {

	private static Connection conn = DatabaseConnection.getConnection();
	
	@Override
	public int add(Student student) throws RestDomeException {
		String Query = "insert into " + Constants.STUDENTS + "  (firstName , lastName , email ,  phone) values (? , ? , ? , ?)";
		
		try {
			PreparedStatement s = conn.prepareStatement(Query);
			s.setString(1, student.getFirstName());
			s.setString(2 , student.getLastName());
			s.setString(3, student.getEmail());
			s.setString(4, student.getPhone());
			int rs = s.executeUpdate();
			
			if(rs == 0)
				throw new SQLException();
			
			return rs;
			
		} catch(SQLException e) {
			throw new RestDomeException("There is an Error in this student may be ID is already exist or Email is empty or etc .. you should try again .. !");
		}
	}

	@Override
	public int delete(int studentID) throws RestDomeException {
		try {
			String Query = "delete from " + Constants.STUDENTS + " where studentID =?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, studentID);
			
			int rs = s.executeUpdate();
			
			if(rs == 0)
				throw new SQLException();
			
			return rs;
			
		} catch(SQLException e) {
			throw new RestDomeException("There is an Error student is already not exist .. !");
		}
	}

	@Override
	public Student getStudent(int studentID) throws RestDomeException {
		try {
			String Query = "select * from "+ Constants.STUDENTS +" where studentID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, studentID);
			
			ResultSet result = s.executeQuery();

			Student stu  = null;
			
			while(result.next()) {
				int studentId = result.getInt("studentID");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String email = result.getString("email");
				String phone = result.getString("phone");
				List<Course> courses= getCourses(studentId);
				
				stu = new Student(studentId, firstName, lastName, email, phone , courses);
			}
			

			
			return stu;
		} catch(SQLException e) {
			throw new RestDomeException("There is an Error student is not exist .. !");
		}
	}
	
	public Student getStudentForOneCourse(int studentID) throws RestDomeException {
		try {
			String Query = "select * from "+ Constants.STUDENTS +" where studentID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, studentID);
			
			ResultSet result = s.executeQuery();

			Student stu  = null;
			
			while(result.next()) {
				int studentId = result.getInt("studentID");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String email = result.getString("email");
				String phone = result.getString("phone");
				
				stu = new Student(studentId, firstName, lastName, email, phone);
			}
			

			
			return stu;
		} catch(SQLException e) {
			throw new RestDomeException("There is an Error student is not exist .. !");
		}
	}

	@Override
	public List<Student> getStudents() throws RestDomeException {
		try {
			String Query = "select * from " + Constants.STUDENTS;
		
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			ResultSet result = s.executeQuery();
			
			List <Student> students = new ArrayList<>();
			
			while(result.next()) {
				int studentId = result.getInt("studentID");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String email = result.getString("email");
				String phone = result.getString("phone");
				List<Course> courses= getCourses(studentId);
				
				students.add(new Student(studentId, firstName, lastName, email, phone , courses));
			}
			
			
			return students;
			
		}catch(SQLException e) {
			throw new RestDomeException("There is no Students .. !");
		}
	}

	@Override
	public int update(Student student) throws RestDomeException {
		try {
			String Query = "update " + Constants.STUDENTS +  " set firstName =? , lastName =? , phone =? , email =? where studentID =?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setString(1, student.getFirstName());
			s.setString(2, student.getLastName());
			s.setString(3, student.getPhone());
			s.setString(4, student.getEmail());
			s.setInt(5, student.getStudentID());
			
			int rs = s.executeUpdate();
			
			if(rs == 0)
				throw new SQLException();
			
			return rs;
		} catch(SQLException e) {
			throw new RestDomeException("There is no Student to update it .. !");
		}
	}

	@Override
	public int subscribe(int studentID, int courseID) throws RestDomeException {
		try {
			if(!isAvailable(courseID)) 
				throw new SQLException();
			
			String Query = "insert into " + Constants.REGISTRATIONS + " (studentID , courseID) values (?,?)";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, studentID);
			s.setInt(2, courseID);
			
			int rs = s.executeUpdate();
		
			if(rs == 0)
				throw new SQLException();
				
			return rs;
		} catch(SQLException e) {
			throw new RestDomeException("You Should be focus may be Student is already subscribed with this course or course is already filled with " + Constants.COURSELIMIT + " students ..!");
		}
	}

	@Override
	public int unSubscribe(int studentID, int courseID) throws RestDomeException {
		try {
			
			String Query = "delete from " + Constants.REGISTRATIONS + " where courseID=? and studentID=?";
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1 , courseID);
			s.setInt(2, studentID);
			
			int rs = s.executeUpdate();
		
			if(rs == 0)
				throw new SQLException();
				
			return rs;
		} catch(SQLException e) {
			throw new RestDomeException("Oops..!, There is no subscribtion for this student in course to delete it  .. !");
		}
	}
	
	@Override
	public List<Course> getCourses(int studentID) throws RestDomeException {
		try {
			String Query = "select courseID from " + Constants.REGISTRATIONS + " where studentID = ?";
			
			CourseDaoImplementation courseDao = new CourseDaoImplementation();
			
			PreparedStatement s = conn.prepareStatement(Query);
			
			s.setInt(1, studentID);
			
			ResultSet rs = s.executeQuery();
			
			List <Course> courses = new ArrayList<>();
			
			while(rs.next()){
				int courseID = rs.getInt("courseID");
				courses.add(courseDao.getCourseForOneStudent(courseID)) ;
			}
			
			return courses;
		} catch(SQLException e) {
			throw new RestDomeException("There is no Courses .. !");
		}
	}

	private boolean isAvailable(int courseID) throws SQLException, RestDomeException{
		
		CourseDaoImplementation courseDao = new CourseDaoImplementation();
		
		Course c = courseDao.getCourse(courseID);
		
		if(c == null)
			return true;
		
		if(c.getStudents() == null)
			return true;
		
		return (c.getStudents().size() < Constants.COURSELIMIT) ?  true :  false; 
	}
}
