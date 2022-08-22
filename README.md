# Rest-Services-Example
                                                          REST SERVICES IN JAVA
•	For implementing rest services in java, install spring tool suite, an ide which is very similar to eclipse. Install postman and create a login for the same if not available.

•	The following are the steps for implementing restful services:-
1.	Create the Spring Boot project
2.	Create the Beans
3.	Create the Endpoints
4.	Mark them with Annotations
5.	Configure

•	As an example for project, perform CRUD operations for courses with parameters id, price, rating, name and taughtby

•	Create a Spring starter project with name coursews in STS. The maven dependency for spring would be added by default. While creating project, check for apache cxf dependency, if not available, manually add the latest apache cxf from maven repository. Below is an example, check for latest
 <!-- https://mvnrepository.com/artifact/org.apache.cxf/cxf-spring-boot-starter-jaxrs -->
<dependency>
    <groupId>org.apache.cxf</groupId>
    <artifactId>cxf-spring-boot-starter-jaxrs</artifactId>
    <version>3.5.3</version>
</dependency>
Add this dependency in pom.xml in middle of dependency for spring boot. Empty project is ready for creating rest api

•	Mainly we are focusing on performing CRUD operations for rest api. In Rest, there are four apis for the same:- GET, POST, PUT, DELETE
GET - For getting the data/ read operation
POST - For creating the data
PUT - For updating the data
DELETE - For deleting the data

•	Create two packages and following classes of packages as mentioned below: -
1.	under src/main/java
com.stark.coursews
--> CourseService.Java
--> CourseServiceImpl.Java
--> CoursewsApplication.Java
com.stark.coursews.model
--> Course.Java
2.	under src/main/resources
--> Modify application.properties by adding two properties
	cxf.jaxrs.component-scan=true
	server.servlet.context-path=/coursews
       Note: - If you are not getting the exact property, search for a similar one
3.	under src/test/java
com.stark.coursews
--> CoursewsApplicationTests.Java

•	Source Code for Course.java:-
package com.stark.coursews.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Course")
public class Course {
	private long id, price, rating;
	private String name, taughtBy;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
public String getTaughtby() {
		return taughtby;
	}
	public void setTaughtby(String taughtby) {
		this.taughtby = taughtby;
	}
}

•	Source Code for CourseService.Java:-
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
	Course getCourse(@PathParam("ident") Long id);

	@Path("/courses")
	@POST
	Response createCourse(Course course);

	@Path("/courses")
	@PUT
	Response updateCourse(Course course);

	@Path("/courses/{id}")
	@DELETE
	Response deleteCourse(@PathParam("id") Long id);

}

•	Source Code for CourseServiceImpl.Java:- 
package com.stark.coursews;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.stark.coursews.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	Map<Long, Course> courses = new HashMap<>();
	long currentId = 123, currentprice = 458;

	public CourseServiceImpl() {
		init();
	}

	void init() {
		Course course = new Course();
		course.setId(currentId);
		course.setName("Tony Stark");
		course.setPrice(currentprice);
		course.setRating(4);
		course.setTaughtby("IronMan");
		courses.put(course.getId(), course);
	}

	@Override
	public List<Course> getCourses() {
		Collection<Course> results = courses.values();
		List<Course> response = new ArrayList<>(results);
		return response;
	}

	@Override
	public Course getCourse(Long id) {
		if (courses.get(id) == null) {
			throw new NotFoundException();
		}
		return courses.get(id);
	}

	@Override
	public Response createCourse(Course course) {
		course.setId(++currentId);
		courses.put(course.getId(), course);
		return Response.ok(course).build();
	}

	@Override
	public Response updateCourse(Course course) {

		Course currentCourse = courses.get(course.getId());

		Response response;
		if (currentCourse != null) {
			courses.put(course.getId(), course);
			response = Response.ok().build();
		} else {
			throw new CourseBusinessException();
		}
		return response;
	}

	@Override
	public Response deleteCourse(Long id) {
		Course course = courses.get(id);

		Response response;

		if (course != null) {
			courses.remove(id);
			response = Response.ok().build();
		} else {
			response = Response.notModified().build();
		}
		return response;
	}
}

•	Source Code for CoursewsApplication.Java:-
package com.stark.coursews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursewsApplication.class, args);
	}
}

•	Source Code for CoursewsApplicationTests.Java:-
package com.stark.coursews;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoursewsApplicationTests {

	@Test
	void contextLoads() {
	}
}

•	Run the Spring project and troubleshoot if there are any issues. Once everything is fine,open Postman and check for GET, POST, PUT, DELETE in the url mentioned below:-
localhost:8080/coursews/services/courseservice/courses


•	For displaying the results in json format, we need to configure using following steps:-
	1.	Modify pom.xml adding dependency of jax-rs
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-jaxrs</artifactId>
			<version>1.9.13</version>
		</dependency>

		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-xc</artifactId>
			<version>1.9.13</version>
		</dependency>
	2.	Modify application.properties in the following format, check for the available classes:-
		#cxf.jaxrs.component-scan=true
		cxf.jaxrs.classes-scan=true
		cxf.jaxrs.classes-scan-packages=org.codehaus.jackson.jaxrs,com.stark.coursews
	3.	Check for CourseService.java having following format in the code:-
		@Consumes("application/xml,application/json")
		@Produces("application/xml,application/json")
	4.	If not receiving values in json, In postman, check for cookies, switch to headers adjacent to Authorization, under key column type "Accept" 		    and under value column type "application/json"

