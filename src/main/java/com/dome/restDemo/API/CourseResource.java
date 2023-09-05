package com.dome.restDemo.API;


import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dome.restDemo.Model.Course;
import com.dome.restDemo.Model.RestDomeException;
import com.dome.restDemo.services.CourseServices;

@Path("course")
public class CourseResource {
	
	private static CourseServices courseServices = new CourseServices();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourses(){
		try {
			return Response.ok().entity(courseServices.getCourses()).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourse(@PathParam("id") int courseID){
		try {
			return Response.ok().entity(courseServices.getCourse(courseID)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setCourse(Course course){
		try {
			return Response.ok().entity(courseServices.setCourse(course)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCourse(Course course){
		try {
			return Response.ok().entity(courseServices.updateCourse(course)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delCourse(@PathParam("id") int courseID){
		try {
			return Response.ok().entity(courseServices.delCourse(courseID)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/{id}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentsForOneCourse(@PathParam("id") int courseID) throws RestDomeException{
		try {
			return Response.ok().entity(courseServices.getStudentsForOneCourse(courseID)).build();
		} catch(RestDomeException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
	}
}
