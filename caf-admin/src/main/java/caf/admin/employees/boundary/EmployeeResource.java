package caf.admin.employees.boundary;

import caf.admin.employees.control.EmployeeDAO;
import caf.admin.employees.entity.Employee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	private final EmployeeDAO employeeDAO;

	@Inject
	public EmployeeResource(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;

	}

	@GET
	public Response getEmployees() {

		return Response.ok(employeeDAO.find().asList()).build();
	}

	@POST
	public Response saveEmployee(Employee emp, @Context UriInfo uriInfo) {

		Object resourceId = employeeDAO.save(emp).getId();
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(resourceId.toString());
		return Response.created(builder.build()).build();

	}

	@GET
	@Path("/{id}")
	public Response getEmployeeById(@PathParam("id") String id) {
		return Response.ok(employeeDAO.findById(id)).build();
	}

}