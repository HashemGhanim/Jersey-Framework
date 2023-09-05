package com.dome.restDemo.API;



import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dome.restDemo.Model.Registration;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.Model.Student;
import com.dome.restDemo.services.StudentServices;

@Path("student")
public class StudentResource {
	
	private static StudentServices studentServices = new StudentServices();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudents() {
		try {
			return Response.ok().entity(studentServices.getStudents()).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@PathParam("id") int studentID){
		try {
			return Response.ok().entity(studentServices.getStudent(studentID)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setStudent(Student student) {
		try {
			return Response.ok().entity(studentServices.setStudent(student)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent(Student student){
		try {
			return Response.ok().entity(studentServices.updateStudent(student)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delStudent(@PathParam("id") int studentID){
		try {
			return Response.ok().entity(studentServices.delStudent(studentID)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response subscribe(Registration registration){
		try {
			return Response.ok().entity(studentServices.subscribe(registration.getStudentID() , registration.getCourseID())).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@DELETE
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response unSubscribe(Registration registration){
		try {
			return Response.ok().entity(studentServices.unSubscribe(registration.getStudentID() , registration.getCourseID())).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/{id}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourses(@PathParam("id") int studentID){
		try {
			return Response.ok().entity(studentServices.getCourses(studentID)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	
}
