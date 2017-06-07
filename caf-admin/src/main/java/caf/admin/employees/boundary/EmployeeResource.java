package caf.admin.employees.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import caf.admin.employees.control.EmployeeService;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	private final EmployeeService employeeSrv;

	@Inject
	public EmployeeResource(EmployeeService empSrv) {
		this.employeeSrv = empSrv;
	}

	@GET
	public Response getEmployees() {

		return Response.ok(employeeSrv.getEmployees()).build();
	}

	@GET
	@Path("/{id}")
	public Response getEmployeeById(@PathParam("id") String id) {
		return Response.ok(employeeSrv.findById(id)).build();
	}

}