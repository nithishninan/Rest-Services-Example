package com.stark.coursews;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.stark.coursews.model.Course;

@Consumes("application/xml,application/json")
@Produces("application/xml,application/json")
@Path("/courseservice")
public interface CourseService {
	
	@Path("/courses")
	@GET
	List<Course> getCourses();
	
	@Path("/courses/{ident}")
	@GET
	Course getCourses(@javax.ws.rs.PathParam(value = "ident") Long id);
	
	@Path("/courses")
	@POST
	Response createCourse(Course course);
	
	@Path("/courses")
	@PUT
	Response updateCourse(Course course);
	
	@Path("/courses/{id}")
	@DELETE
	Response deleteCourse(@PathParam(value = "id") Long id);
}